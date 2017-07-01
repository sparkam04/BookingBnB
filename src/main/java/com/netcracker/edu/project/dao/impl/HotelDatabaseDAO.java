package com.netcracker.edu.project.dao.impl;

import com.netcracker.edu.project.dao.HotelDAO;
import com.netcracker.edu.project.model.Hotel;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Repository
public class HotelDatabaseDAO extends AbstractDatabaseDAO<Hotel> implements HotelDAO {

    HotelDatabaseDAO() {
        setObjectTypeId(4);
    }

    @Override
    public Hotel getById(Long id) {
        Hotel hotel;
        String sql = "select hotel.object_id\n" +
                "    , hotel_name.value hotel_name\n" +
                "    , owner_id_ref.REFERENCE owner_id_ref\n" +
                "    , hotel.PARENT_ID location_id\n" +
                "    , phone.VALUE phone\n" +
                "    , description.VALUE description\n" +
                "    , hotel_rating.VALUE hotel_rating\n" +
                "    , has_wifi.VALUE has_wifi\n" +
                "    , has_shuttle.VALUE has_shuttle\n" +
                "    , has_smoking.VALUE has_smoking\n" +
                "    , has_parking.VALUE has_parking\n" +
                "    , has_conditioning.VALUE has_conditioning\n" +
                "    , has_pets.VALUE has_pets\n" +
                "    , has_pool.VALUE has_pool\n" +
                "    , has_kitchen.VALUE has_kitchen\n" +
                "    , has_breakfast.VALUE has_breakfast\n" +
                "    , check_in_time.VALUE check_in_time\n" +
                "    , check_out_time.VALUE check_out_time\n" +
                "    , is_preorder.VALUE is_preorder\n" +
                "from OBJECTS hotel\n" +
                "    join attributes hotel_name on hotel_name.OBJECT_ID = hotel.OBJECT_ID and hotel_name.ATTR_ID = 9\n" +
                "    join attributes phone on phone.OBJECT_ID = hotel.OBJECT_ID and phone.ATTR_ID = 12\n" +
                "    join attributes description on description.OBJECT_ID = hotel.OBJECT_ID and description.ATTR_ID = 13\n" +
                "    join attributes hotel_rating on hotel_rating.OBJECT_ID = hotel.OBJECT_ID and hotel_rating.ATTR_ID = 14\n" +
                "    join attributes has_wifi on has_wifi.OBJECT_ID = hotel.OBJECT_ID and has_wifi.ATTR_ID = 15\n" +
                "    join attributes has_shuttle on has_shuttle.OBJECT_ID = hotel.OBJECT_ID and has_shuttle.ATTR_ID = 16\n" +
                "    join attributes has_smoking on has_smoking.OBJECT_ID = hotel.OBJECT_ID and has_smoking.ATTR_ID = 17\n" +
                "    join attributes has_parking on has_parking.OBJECT_ID = hotel.OBJECT_ID and has_parking.ATTR_ID = 18\n" +
                "    join attributes has_conditioning on has_conditioning.OBJECT_ID = hotel.OBJECT_ID and has_conditioning.ATTR_ID = 19\n" +
                "    join attributes has_pets on has_pets.OBJECT_ID = hotel.OBJECT_ID and has_pets.ATTR_ID = 20\n" +
                "    join attributes has_pool on has_pool.OBJECT_ID = hotel.OBJECT_ID and has_pool.ATTR_ID = 21\n" +
                "    join attributes has_kitchen on has_kitchen.OBJECT_ID = hotel.OBJECT_ID and has_kitchen.ATTR_ID = 22\n" +
                "    join attributes has_breakfast on has_breakfast.OBJECT_ID = hotel.OBJECT_ID and has_breakfast.ATTR_ID = 23\n" +
                "    join attributes check_in_time on check_in_time.OBJECT_ID = hotel.OBJECT_ID and check_in_time.ATTR_ID = 24\n" +
                "    join attributes check_out_time on check_out_time.OBJECT_ID = hotel.OBJECT_ID and check_out_time.ATTR_ID = 25\n" +
                "    join attributes is_preorder on is_preorder.OBJECT_ID = hotel.OBJECT_ID and is_preorder.ATTR_ID = 26\n" +
                "\n" +
                "    join OBJREFERENCE owner_id_ref on owner_id_ref.OBJECT_ID = hotel.OBJECT_ID\n" +
                "where\n" +
                "    hotel.OBJECT_TYPE_ID = 4\n" +
                "    and owner_id_ref.ATTR_ID = 10\n" +
                "    and hotel.OBJECT_ID = ?";

        String sql_paySystems = "select \n" +
                "    pay_sys_ref.REFERENCE pay_sys_id\n" +
                "from OBJECTS hotel\n" +
                "    join OBJREFERENCE pay_sys_ref on pay_sys_ref.OBJECT_ID = hotel.OBJECT_ID\n" +
                "where\n" +
                "    hotel.OBJECT_TYPE_ID = 4\n" +
                "    and pay_sys_ref.ATTR_ID = 27\n" +
                "    and hotel.OBJECT_ID = ?";

        String sql_Hotel_images = "select \n" +
                "    hotel_image.REFERENCE hotel_image\n" +
                "from OBJECTS hotel\n" +
                "    join OBJREFERENCE hotel_image on hotel_image.OBJECT_ID = hotel.OBJECT_ID\n" +
                "where\n" +
                "    hotel.OBJECT_TYPE_ID = 4\n" +
                "    and hotel_image.ATTR_ID = 57\n" +
                "    and hotel.OBJECT_ID = ?";

        Collection<Long> paySystems;
        Collection<Long> hotelImages;

        hotel = getJdbcTemplate().queryForObject(sql, new Object[]{id}, new HotelMapper());
        paySystems = getJdbcTemplate().queryForList(sql_paySystems, new Object[]{id}, Long.TYPE);
        hotelImages = getJdbcTemplate().queryForList(sql_Hotel_images, new Object[]{id}, Long.TYPE);

        hotel.setPaySysIds(paySystems);
        hotel.setImages(hotelImages);

        return hotel;
    }

    @Override
    public Double getRatingById(Long id) {
        String sql = "SELECT AVG(RATES.VALUE)\n" +
                "FROM ATTRIBUTES RATES JOIN OBJECTS RATINGS ON (RATES.ATTR_ID = 55 AND RATINGS.OBJECT_TYPE_ID = 13 AND RATES.OBJECT_ID = RATINGS.OBJECT_ID)\n" +
                "  JOIN OBJECTS BOOKINGS ON (BOOKINGS.OBJECT_TYPE_ID = 7 AND RATINGS.PARENT_ID = BOOKINGS.OBJECT_ID)\n" +
                "  JOIN OBJECTS ROOMS ON (ROOMS.OBJECT_TYPE_ID = 5 AND BOOKINGS.PARENT_ID = ROOMS.OBJECT_ID AND ROOMS.PARENT_ID = ?)";
        Double rating = getJdbcTemplate().queryForObject(sql, Double.TYPE, id);
        sql = "UPDATE ATTRIBUTES A\n" +
                "  SET A.VALUE = ? \n" +
                "  WHERE A.OBJECT_ID = ? AND A.ATTR_ID = 14";
        getJdbcTemplate().update(sql, rating, id);
        return rating;
    }

    @Override
    public boolean add(Hotel model) {
        String sqlNewObjId = "select object_id_seq.nextval new_id from dual";
        Long newObjId = getJdbcTemplate().queryForObject(sqlNewObjId, Long.TYPE);

        String sql = "INSERT ALL\n" +
                "    INTO objects (object_id, PARENT_ID, object_type_id, name)\n" +
                "        VALUES (?, ?, 4, ?) --location_id, hotel_name\n" +
                "    INTO attributes (attr_id, object_id, value)\n" +
                "        VALUES (9, ?, ?) --hotel_name\n" +
                "    INTO attributes (attr_id, object_id, value)\n" +
                "        VALUES (12, ?, ?) --phone\n" +
                "    INTO attributes (attr_id, object_id, value)\n" +
                "        VALUES (13, ?, ?) --description\n" +
                "    INTO attributes (attr_id, object_id, value)\n" +
                "        VALUES (14, ?, ?) --rating\n" +
                "    INTO attributes (attr_id, object_id, value)\n" +
                "        VALUES (15, ?, ?) --has_wifi\n" +
                "    INTO attributes (attr_id, object_id, value)\n" +
                "        VALUES (16, ?, ?)\n" +
                "    INTO attributes (attr_id, object_id, value)\n" +
                "        VALUES (17, ?, ?) \n" +
                "    INTO attributes (attr_id, object_id, value)\n" +
                "        VALUES (18, ?, ?)\n" +
                "    INTO attributes (attr_id, object_id, value)\n" +
                "        VALUES (19, ?, ?)\n" +
                "    INTO attributes (attr_id, object_id, value)\n" +
                "        VALUES (20, ?, ?)\n" +
                "    INTO attributes (attr_id, object_id, value)\n" +
                "        VALUES (21, ?, ?)\n" +
                "    INTO attributes (attr_id, object_id, value)\n" +
                "        VALUES (22, ?, ?)\n" +
                "    INTO attributes (attr_id, object_id, value)\n" +
                "        VALUES (23, ?, ?) --has_breakfast\n" +
                "    INTO attributes (attr_id, object_id, value)\n" +
                "        VALUES (24, ?, ?) --check_in_time\n" +
                "    INTO attributes (attr_id, object_id, value)\n" +
                "        VALUES (25, ?, ?) --check_out_time\n" +
                "    INTO attributes (attr_id, object_id, value)\n" +
                "        VALUES (26, ?, ?) --is_preorder\n" +
                "        \n" +
                "    INTO OBJREFERENCE (attr_id, reference, object_id)\n" +
                "        VALUES (10, ?, ?) --owner_id\n" +
                "select * from dual";

        int affrctedRows = getJdbcTemplate().update(sql,
                newObjId, model.getLocationId(), model.getHotelName(),
                newObjId, model.getHotelName(),
                newObjId, model.getPhone(),
                newObjId, model.getDescription(),
                newObjId, model.getHotelRating(),
                newObjId, model.isHasWifi().toString(),
                newObjId, model.isHasShuttle().toString(),
                newObjId, model.isHasSmoking().toString(),
                newObjId, model.isHasParking().toString(),
                newObjId, model.isHasConditioning().toString(),
                newObjId, model.isHasPets().toString(),
                newObjId, model.isHasPool().toString(),
                newObjId, model.isHasKitchen().toString(),
                newObjId, model.isHasBreakfast().toString(),
                newObjId, model.getCheckInTime().toString(),
                newObjId, model.getCheckOutTime().toString(),
                newObjId, model.isPreorder().toString(),
                model.getOwnerId(), newObjId);

        boolean isSuccess1 = affrctedRows == 18;

        boolean isSuccess2 = batchInsertObjReferences(27, model.getPaySysIds(), newObjId);

        boolean isSuccess3 = batchInsertObjReferences(57, model.getImages(), newObjId);

        return isSuccess1 && isSuccess2 && isSuccess3;
    }

    @Override
    public boolean update(Hotel model) {
        String sql1 = "UPDATE objects SET PARENT_ID = ?, NAME = ? WHERE object_type_id = 4 and object_id = ?";
        String sql3 = "UPDATE ATTRIBUTES SET VALUE = ? WHERE object_id = ? and attr_id = ?";

        boolean success = 1 == getJdbcTemplate().update(sql1, model.getLocationId(), model.getHotelName(), model.getId());

        boolean success1 = batchInsertObjReferences(10, Arrays.asList(model.getOwnerId()), model.getId());

        List<Object[]> arguments = new ArrayList<Object[]>() {
            {
                add(new Object[]{model.getHotelName(), model.getId(), 9});
                add(new Object[]{model.getPhone(), model.getId(), 12});
                add(new Object[]{model.getDescription(), model.getId(), 13});
                add(new Object[]{model.getHotelRating(), model.getId(), 14});
                add(new Object[]{model.isHasWifi().toString(), model.getId(), 15});
                add(new Object[]{model.isHasShuttle().toString(), model.getId(), 16});
                add(new Object[]{model.isHasSmoking().toString(), model.getId(), 17});
                add(new Object[]{model.isHasParking().toString(), model.getId(), 18});
                add(new Object[]{model.isHasConditioning().toString(), model.getId(), 19});
                add(new Object[]{model.isHasPets().toString(), model.getId(), 20});
                add(new Object[]{model.isHasPool().toString(), model.getId(), 21});
                add(new Object[]{model.isHasKitchen().toString(), model.getId(), 22});
                add(new Object[]{model.isHasBreakfast().toString(), model.getId(), 23});
                add(new Object[]{model.getCheckInTime().toString(), model.getId(), 24});
                add(new Object[]{model.getCheckOutTime().toString(), model.getId(), 25});
                add(new Object[]{model.isPreorder().toString(), model.getId(), 26});
            }
        };

        int[] response = getJdbcTemplate().batchUpdate(sql3, arguments);

        boolean success2 = true;

        for (int i : response) {
            if (i != -2) {
                success2 = false;
                break;
            }
        }

        //update images
        boolean success3 = batchInsertObjReferences(57, model.getImages(), model.getId());

        //update pay systems
        boolean success4 = batchInsertObjReferences(27, model.getPaySysIds(), model.getId());

        return success && success1 && success2 && success3 && success4;
    }

    private final class HotelMapper implements RowMapper<Hotel> {
        @Override
        public Hotel mapRow(ResultSet rs, int rowNum) throws SQLException {
            Hotel hotel = new Hotel();
            hotel.setId(rs.getLong("object_id"));
            hotel.setHotelName(rs.getString("hotel_name"));
            hotel.setOwnerId(rs.getLong("owner_id_ref"));
            hotel.setLocationId(rs.getLong("location_id"));
            hotel.setPhone(rs.getString("phone"));
            hotel.setDescription(rs.getString("description"));
            hotel.setHotelRating(rs.getDouble("hotel_rating"));
            hotel.setHasWifi(Boolean.parseBoolean(rs.getString("has_wifi")));
            hotel.setHasShuttle(Boolean.parseBoolean(rs.getString("has_shuttle")));
            hotel.setHasSmoking(Boolean.parseBoolean(rs.getString("has_smoking")));
            hotel.setHasParking(Boolean.parseBoolean(rs.getString("has_parking")));
            hotel.setHasConditioning(Boolean.parseBoolean(rs.getString("has_conditioning")));
            hotel.setHasPets(Boolean.parseBoolean(rs.getString("has_pets")));
            hotel.setHasPool(Boolean.parseBoolean(rs.getString("has_pool")));
            hotel.setHasKitchen(Boolean.parseBoolean(rs.getString("has_kitchen")));
            hotel.setHasBreakfast(Boolean.parseBoolean(rs.getString("has_breakfast")));
            hotel.setCheckInTime(Time.valueOf(rs.getString("check_in_time")));
            hotel.setCheckOutTime(Time.valueOf(rs.getString("check_out_time")));
            hotel.setPreorder(Boolean.parseBoolean(rs.getString("is_preorder")));
            return hotel;
        }
    }


}
