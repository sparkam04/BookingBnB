package com.netcracker.edu.project.dao.impl;

import com.netcracker.edu.project.dao.RoomDAO;
import com.netcracker.edu.project.model.Room;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public class RoomDatabaseDAO extends AbstractDatabaseDAO<Room> implements RoomDAO {

    RoomDatabaseDAO() {
        setObjectTypeId(5);
    }

    @Override
    public Room getById(Long id) {
        Room room;
        String sql = "select room.object_id\n" +
                "    , room.NAME room_name\n" +
                "    , room.parent_id hotel_id\n" +
                "    , room_no.VALUE room_no\n" +
                "    , num_places.VALUE num_places\n" +
                "    , has_bathroom.VALUE has_bathroom\n" +
                "    , has_tv.VALUE has_tv\n" +
                "    , has_extra_bed.VALUE has_extra_bed\n" +
                "    , cost.VALUE cost\n" +
                "from objects room\n" +
                "join attributes room_no on room_no.OBJECT_ID = room.OBJECT_ID and room_no.ATTR_ID = 28\n" +
                "join attributes num_places on num_places.OBJECT_ID = room.OBJECT_ID and num_places.ATTR_ID = 29\n" +
                "join attributes has_bathroom on has_bathroom.OBJECT_ID = room.OBJECT_ID and has_bathroom.ATTR_ID = 30\n" +
                "join attributes has_tv on has_tv.OBJECT_ID = room.OBJECT_ID and has_tv.ATTR_ID = 31\n" +
                "join attributes has_extra_bed on has_extra_bed.OBJECT_ID = room.OBJECT_ID and has_extra_bed.ATTR_ID = 32\n" +
                "join attributes cost on cost.OBJECT_ID = room.OBJECT_ID and cost.ATTR_ID = 33\n" +
                "where room.OBJECT_TYPE_ID = 5\n" +
                "    and room.OBJECT_ID = ?";

        String sql_Room_images = "select room_image.REFERENCE room_image\n" +
                "from OBJECTS room\n" +
                "join OBJREFERENCE room_image on room_image.OBJECT_ID = room.OBJECT_ID\n" +
                "where\n" +
                "    room.OBJECT_TYPE_ID = 5\n" +
                "    and room_image.ATTR_ID = 59\n" +
                "    and room.OBJECT_ID = ?";

        Collection<Long> roomImages;

        room = getJdbcTemplate().queryForObject(sql, new Object[]{id}, new RoomMapper());
        roomImages = getJdbcTemplate().queryForList(sql_Room_images, new Object[]{id}, Long.TYPE);

        room.setImages(roomImages);

        return room;
    }

    private final class RoomMapper implements RowMapper<Room> {
        @Override
        public Room mapRow(ResultSet rs, int rowNum) throws SQLException {
            Room room = new Room();
            room.setId(rs.getLong("object_id"));
            room.setRoomName(rs.getString("room_name"));
            room.setHotelId(rs.getLong("hotel_id"));
            room.setRoomNumber(rs.getInt("room_no"));
            room.setNumOfPlaces(rs.getInt("num_places"));
            room.setHasBathroom(Boolean.parseBoolean(rs.getString("has_bathroom")));
            room.setHasTV(Boolean.parseBoolean(rs.getString("has_tv")));
            room.setHasExtraBed(Boolean.parseBoolean(rs.getString("has_extra_bed")));
            room.setCost(rs.getDouble("cost"));
            return room;
        }
    }

    @Override
    public boolean add(Room model) {
        String sqlNewObjId = "select object_id_seq.nextval new_id from dual";
        Long newObjId = getJdbcTemplate().queryForObject(sqlNewObjId, Long.TYPE);

        String sql = "INSERT ALL\n" +
                "    INTO objects (object_id, PARENT_ID, object_type_id, name)\n" +
                "        VALUES (?, ?, 5, ?) --object_id, hotel_id, room_name\n" +
                "    INTO attributes (attr_id, object_id, value)\n" +
                "        VALUES (28, ?, ?) --room_no\n" +
                "    INTO attributes (attr_id, object_id, value)\n" +
                "        VALUES (29, ?, ?) --num_places\n" +
                "    INTO attributes (attr_id, object_id, value)\n" +
                "        VALUES (30, ?, ?) --has_bathroom\n" +
                "    INTO attributes (attr_id, object_id, value)\n" +
                "        VALUES (31, ?, ?) --has_tv\n" +
                "    INTO attributes (attr_id, object_id, value)\n" +
                "        VALUES (32, ?, ?) --has_extra_bed\n" +
                "    INTO attributes (attr_id, object_id, value)\n" +
                "        VALUES (33, ?, ?) --cost\n" +
                "select * from dual";

        int affrctedRows = getJdbcTemplate().update(sql,
                newObjId, model.getHotelId(), model.getRoomName(),
                newObjId, model.getRoomNumber(),
                newObjId, model.getNumOfPlaces(),
                newObjId, model.isHasBathroom().toString(),
                newObjId, model.isHasTV().toString(),
                newObjId, model.isHasExtraBed().toString(),
                newObjId, model.getCost());

        boolean isSuccess1 = affrctedRows == 7;

        boolean isSuccess2 = batchInsertObjReferences(59, model.getImages(), newObjId);

        return isSuccess1 && isSuccess2;
    }

    @Override
    public boolean update(Room model) {
        String sql1 = "UPDATE objects SET PARENT_ID = ?, NAME = ? WHERE object_type_id = 5 and object_id = ?";
        String sql2 = "UPDATE ATTRIBUTES SET VALUE = ? WHERE object_id = ? and attr_id = ?";

        boolean success = (1 == getJdbcTemplate().update(sql1, model.getHotelId(), model.getRoomName(), model.getId()));

        List<Object[]> arguments = new ArrayList<Object[]>() {
            {
                add(new Object[]{model.getRoomNumber(), model.getId(), 28});
                add(new Object[]{model.getNumOfPlaces(), model.getId(), 29});
                add(new Object[]{model.isHasBathroom().toString(), model.getId(), 30});
                add(new Object[]{model.isHasTV().toString(), model.getId(), 31});
                add(new Object[]{model.isHasExtraBed().toString(), model.getId(), 32});
                add(new Object[]{model.getCost(), model.getId(), 33});
            }
        };

        int[] response = getJdbcTemplate().batchUpdate(sql2, arguments);

        boolean success2 = true;

        for (int i : response) {
            if (i != -2) {
                success2 = false;
                break;
            }
        }

        //update images
        boolean success3 = batchInsertObjReferences(59, model.getImages(), model.getId());

        return success && success2 && success3;
    }


}
