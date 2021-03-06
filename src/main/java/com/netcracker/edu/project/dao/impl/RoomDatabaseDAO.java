package com.netcracker.edu.project.dao.impl;

import com.netcracker.edu.project.dao.RoomDAO;
import com.netcracker.edu.project.model.Room;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
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
        String sql = "SELECT ROOM.OBJECT_ID\n" +
                "                    , ROOM.NAME ROOM_NAME\n" +
                "                    , ROOM.PARENT_ID HOTEL_ID\n" +
                "                    , ROOM_NO.VALUE ROOM_NO\n" +
                "                    , NUM_PLACES.VALUE NUM_PLACES\n" +
                "                    , HAS_BATHROOM.VALUE HAS_BATHROOM\n" +
                "                    , HAS_TV.VALUE HAS_TV\n" +
                "                    , HAS_EXTRA_BED.VALUE HAS_EXTRA_BED\n" +
                "                    , COST.VALUE COST\n" +
                "                    , IS_ENABLED.VALUE IS_ENABLED\n" +
                "                FROM OBJECTS ROOM\n" +
                "                JOIN ATTRIBUTES ROOM_NO ON ROOM_NO.OBJECT_ID = ROOM.OBJECT_ID AND ROOM_NO.ATTR_ID = 28\n" +
                "                JOIN ATTRIBUTES NUM_PLACES ON NUM_PLACES.OBJECT_ID = ROOM.OBJECT_ID AND NUM_PLACES.ATTR_ID = 29\n" +
                "                JOIN ATTRIBUTES HAS_BATHROOM ON HAS_BATHROOM.OBJECT_ID = ROOM.OBJECT_ID AND HAS_BATHROOM.ATTR_ID = 30\n" +
                "                JOIN ATTRIBUTES HAS_TV ON HAS_TV.OBJECT_ID = ROOM.OBJECT_ID AND HAS_TV.ATTR_ID = 31\n" +
                "                JOIN ATTRIBUTES HAS_EXTRA_BED ON HAS_EXTRA_BED.OBJECT_ID = ROOM.OBJECT_ID AND HAS_EXTRA_BED.ATTR_ID = 32\n" +
                "                JOIN ATTRIBUTES COST ON COST.OBJECT_ID = ROOM.OBJECT_ID AND COST.ATTR_ID = 33\n" +
                "                JOIN ATTRIBUTES IS_ENABLED ON IS_ENABLED.OBJECT_ID = ROOM.OBJECT_ID AND IS_ENABLED.ATTR_ID = 61\n" +
                "                WHERE ROOM.OBJECT_TYPE_ID = 5\n" +
                "                    AND ROOM.OBJECT_ID = ?";

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

    @Override
    public Collection<Room> getFreeRoomsByDateByCity(Date checkIn, Date checkOut, Long cityId) {
        String sql = "SELECT ROOMS.OBJECT_ID\n" +
                "FROM OBJECTS ROOMS JOIN OBJECTS HOTELS ON\n" +
                "  (ROOMS.OBJECT_TYPE_ID = 5 AND HOTELS.OBJECT_TYPE_ID = 4 AND ROOMS.PARENT_ID = HOTELS.OBJECT_ID)\n" +
                "JOIN OBJECTS LOCATIONS ON\n" +
                "  (LOCATIONS.OBJECT_TYPE_ID = 3 AND LOCATIONS.PARENT_ID = ? AND HOTELS.PARENT_ID = LOCATIONS.OBJECT_ID)\n" +
                "JOIN ATTRIBUTES ENABLED ON\n" +
                "  (ENABLED.ATTR_ID = 61 AND ROOMS.OBJECT_ID = ENABLED.OBJECT_ID)\n" +
                "WHERE ENABLED.VALUE <> 'false' AND NOT EXISTS (\n" +
                "  SELECT *\n" +
                "  FROM OBJECTS BOOKING JOIN ATTRIBUTES CHECK_IN ON\n" +
                "    (BOOKING.OBJECT_TYPE_ID = 7 AND CHECK_IN.ATTR_ID = 38 AND BOOKING.OBJECT_ID = CHECK_IN.OBJECT_ID)\n" +
                "  JOIN ATTRIBUTES CHECK_OUT ON\n" +
                "    (CHECK_OUT.ATTR_ID = 39 AND BOOKING.OBJECT_ID = CHECK_OUT.OBJECT_ID)                  \n" +
                "  JOIN OBJREFERENCE STATUS_ID ON\n" +
                "    (STATUS_ID.ATTR_ID = 42 AND BOOKING.OBJECT_ID = STATUS_ID.OBJECT_ID)\n" +
                "  WHERE BOOKING.PARENT_ID = ROOMS.OBJECT_ID AND STATUS_ID.REFERENCE <> 29 AND STATUS_ID.REFERENCE <> 32 AND\n" +
                "  TO_DATE(GREATEST(CHECK_IN.DATE_VALUE,?),'dd.mm.yyyy') <= TO_DATE(LEAST(CHECK_OUT.DATE_VALUE,?),'dd.mm.yyyy')\n" +
                ")";
        List<Long> roomIdList = getJdbcTemplate().queryForList(sql, Long.TYPE, cityId, checkIn, checkOut);
        return getEntityCollection(roomIdList);
    }

    @Override
    public Collection<Room> getBusyRoomsByDateByHotel(Date checkIn, Date checkOut, Long hotelId) {
        String sql = "SELECT DISTINCT ROOMS.OBJECT_ID\n" +
                "FROM OBJECTS ROOMS JOIN OBJECTS BOOKING ON \n" +
                "  (ROOMS.OBJECT_TYPE_ID = 5 AND BOOKING.OBJECT_TYPE_ID = 7 AND ROOMS.PARENT_ID = ? AND BOOKING.PARENT_ID = ROOMS.OBJECT_ID)\n" +
                "JOIN ATTRIBUTES CHECK_IN ON \n" +
                "  (CHECK_IN.ATTR_ID = 38 AND BOOKING.OBJECT_ID = CHECK_IN.OBJECT_ID)\n" +
                "JOIN ATTRIBUTES CHECK_OUT ON\n" +
                "  (CHECK_OUT.ATTR_ID = 39 AND BOOKING.OBJECT_ID = CHECK_OUT.OBJECT_ID)\n" +
                "JOIN OBJREFERENCE STATUS_ID ON\n" +
                "  (STATUS_ID.ATTR_ID = 42 AND BOOKING.OBJECT_ID = STATUS_ID.OBJECT_ID)\n" +
                "WHERE STATUS_ID.REFERENCE <> 29 AND STATUS_ID.REFERENCE <> 32 AND\n" +
                "  TO_DATE(GREATEST(CHECK_IN.DATE_VALUE,?),'dd.mm.yyyy') <= TO_DATE(LEAST(CHECK_OUT.DATE_VALUE,?),'dd.mm.yyyy')";
        List<Long> entityIdList = getJdbcTemplate().queryForList(sql, Long.TYPE, hotelId, checkIn, checkOut);
        return getEntityCollection(entityIdList);
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
                "    INTO attributes (attr_id, object_id, value)\n" +
                "        VALUES (61, ?, ?) --enabled\n" +
                "select * from dual";

        int affrctedRows = getJdbcTemplate().update(sql,
                newObjId, model.getHotelId(), model.getRoomName(),
                newObjId, model.getRoomNumber(),
                newObjId, model.getNumOfPlaces(),
                newObjId, model.isHasBathroom().toString(),
                newObjId, model.isHasTV().toString(),
                newObjId, model.isHasExtraBed().toString(),
                newObjId, model.getCost(),
                newObjId, model.getEnabled().toString()
        );

        boolean isSuccess1 = affrctedRows == 8;

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
                add(new Object[]{model.getEnabled().toString(), model.getId(), 61});
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
            room.setEnabled(rs.getBoolean("is_enabled"));
            return room;
        }
    }
}
