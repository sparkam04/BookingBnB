package com.netcracker.edu.project.dao.impl;

import com.netcracker.edu.project.dao.BookingDAO;
import com.netcracker.edu.project.model.Booking;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Repository
public class BookingDatabaseDAO extends AbstractDatabaseDAO<Booking> implements BookingDAO {

    BookingDatabaseDAO() {
        setObjectTypeId(7);
    }

    @Override
    public Booking getById(Long id) {
        Booking booking;
        String sql = "SELECT BOOKING.OBJECT_ID\n" +
                "                    , BOOKING.PARENT_ID ROOM_ID\n" +
                "                    , BOOKING_CODE.VALUE BOOKING_CODE\n" +
                "                    , MESSAGE.VALUE MESSAGE\n" +
                "                    , USER_ID.REFERENCE USER_ID\n" +
                "                    , DATE_CHECK_IN.DATE_VALUE DATE_CHECK_IN\n" +
                "                    , DATE_CHECK_OUT.DATE_VALUE DATE_CHECK_OUT\n" +
                "                    , NUM_PERSONS.VALUE NUM_PERSONS\n" +
                "                    , IS_PAID.VALUE IS_PAID\n" +
                "                    , STATUS_ID.REFERENCE STATUS_ID\n" +
                "                    , PAY_SYS_ID.REFERENCE PAY_SYS_ID\n" +
                "                    , COST.VALUE COST\n" +
                "                FROM OBJECTS BOOKING\n" +
                "                JOIN ATTRIBUTES BOOKING_CODE ON BOOKING_CODE.OBJECT_ID = BOOKING.OBJECT_ID AND BOOKING_CODE.ATTR_ID = 35\n" +
                "                JOIN ATTRIBUTES MESSAGE ON MESSAGE.OBJECT_ID = BOOKING.OBJECT_ID AND MESSAGE.ATTR_ID = 36\n" +
                "                JOIN OBJREFERENCE USER_ID ON USER_ID.OBJECT_ID = BOOKING.OBJECT_ID\n" +
                "                JOIN ATTRIBUTES DATE_CHECK_IN ON DATE_CHECK_IN.OBJECT_ID = BOOKING.OBJECT_ID AND DATE_CHECK_IN.ATTR_ID = 38\n" +
                "                JOIN ATTRIBUTES DATE_CHECK_OUT ON DATE_CHECK_OUT.OBJECT_ID = BOOKING.OBJECT_ID AND DATE_CHECK_OUT.ATTR_ID = 39\n" +
                "                JOIN ATTRIBUTES NUM_PERSONS ON NUM_PERSONS.OBJECT_ID = BOOKING.OBJECT_ID AND NUM_PERSONS.ATTR_ID = 40\n" +
                "                JOIN ATTRIBUTES IS_PAID ON IS_PAID.OBJECT_ID = BOOKING.OBJECT_ID AND IS_PAID.ATTR_ID = 41\n" +
                "                JOIN OBJREFERENCE STATUS_ID ON STATUS_ID.OBJECT_ID = BOOKING.OBJECT_ID\n" +
                "                JOIN OBJREFERENCE PAY_SYS_ID ON PAY_SYS_ID.OBJECT_ID = BOOKING.OBJECT_ID\n" +
                "                JOIN ATTRIBUTES COST ON COST.OBJECT_ID = BOOKING.OBJECT_ID AND COST.ATTR_ID = 60\n" +
                "                WHERE BOOKING.OBJECT_TYPE_ID = 7\n" +
                "                    AND USER_ID.ATTR_ID = 37\n" +
                "                    AND STATUS_ID.ATTR_ID = 42\n" +
                "                    AND PAY_SYS_ID.ATTR_ID = 43\n" +
                "                    AND BOOKING.OBJECT_ID = ?";

        booking = getJdbcTemplate().queryForObject(sql, new Object[]{id}, new BookingMapper());

        return booking;
    }

    @Override
    public Collection<Booking> getBookingsByClientId(Long clientId) {
        String sql = "SELECT OBJ.OBJECT_ID\n" +
                "FROM OBJECTS OBJ INNER JOIN OBJREFERENCE OBJREF ON\n" +
                "  (OBJ.OBJECT_TYPE_ID = 7 AND OBJREF.ATTR_ID = 37 AND OBJREF.REFERENCE = ? AND OBJREF.OBJECT_ID = OBJ.OBJECT_ID)";
        List<Long> entityIdList = getJdbcTemplate().queryForList(sql, Long.TYPE, clientId);
        return getEntityCollection(entityIdList);
    }

    @Override
    public Collection<Booking> getBookingsByStatusId(Long statusId) {
        String sql = "SELECT OBJ.OBJECT_ID\n" +
                "FROM OBJECTS OBJ INNER JOIN OBJREFERENCE OBJREF ON \n" +
                "  (OBJ.OBJECT_TYPE_ID = 7 AND OBJREF.ATTR_ID = 42 AND OBJREF.REFERENCE = ? AND OBJREF.OBJECT_ID = OBJ.OBJECT_ID)";
        List<Long> entityIdList = getJdbcTemplate().queryForList(sql, Long.TYPE, statusId);
        return getEntityCollection(entityIdList);
    }

    @Override
    public Collection<Booking> getBookingsByDate(Date from, Date to) {
        String sql = "SELECT BOOKING.OBJECT_ID\n" +
                "FROM OBJECTS BOOKING INNER JOIN ATTRIBUTES CHECK_IN ON \n" +
                "  (BOOKING.OBJECT_TYPE_ID = 7 AND CHECK_IN.ATTR_ID = 38 AND BOOKING.OBJECT_ID = CHECK_IN.OBJECT_ID)\n" +
                "  INNER JOIN ATTRIBUTES CHECK_OUT ON\n" +
                "  (CHECK_OUT.ATTR_ID = 39 AND BOOKING.OBJECT_ID = CHECK_OUT.OBJECT_ID)\n" +
                "WHERE TO_DATE(GREATEST(CHECK_IN.DATE_VALUE,?),'dd.mm.yyyy') < TO_DATE(LEAST(CHECK_OUT.DATE_VALUE,?),'dd.mm.yyyy')";
        List<Long> entityIdList = getJdbcTemplate().queryForList(sql, Long.TYPE, from, to);
        return getEntityCollection(entityIdList);
    }

    @Override
    public Collection<Booking> getBookingsByDateAndHotel(Date from, Date to, Long hotelId) {
        String sql = "SELECT BOOKING.OBJECT_ID\n" +
                "FROM OBJECTS BOOKING JOIN OBJECTS ROOMS ON\n" +
                "  (BOOKING.OBJECT_TYPE_ID = 7 AND ROOMS.OBJECT_TYPE_ID = 5 AND BOOKING.PARENT_ID = ROOMS. OBJECT_ID AND ROOMS.PARENT_ID = ?)\n" +
                "  JOIN ATTRIBUTES CHECK_IN ON \n" +
                "  (CHECK_IN.ATTR_ID = 38 AND BOOKING.OBJECT_ID = CHECK_IN.OBJECT_ID)\n" +
                "  JOIN ATTRIBUTES CHECK_OUT ON\n" +
                "  (CHECK_OUT.ATTR_ID = 39 AND BOOKING.OBJECT_ID = CHECK_OUT.OBJECT_ID)\n" +
                "WHERE TO_DATE(GREATEST(CHECK_IN.DATE_VALUE,?),'dd.mm.yyyy') < TO_DATE(LEAST(CHECK_OUT.DATE_VALUE,?),'dd.mm.yyyy')";
        List<Long> entityIdList = getJdbcTemplate().queryForList(sql, Long.TYPE, hotelId, from, to);
        return getEntityCollection(entityIdList);
    }

    @Override
    public boolean add(Booking model) {
        //CHECK IF ALREADY BOOKED
        String sql = "SELECT BOOKING.OBJECT_ID\n" +
                "FROM OBJECTS BOOKING JOIN ATTRIBUTES CHECK_IN ON \n" +
                "  (BOOKING.OBJECT_TYPE_ID = 7 AND BOOKING.PARENT_ID = ? AND " +
                "       CHECK_IN.ATTR_ID = 38 AND BOOKING.OBJECT_ID = CHECK_IN.OBJECT_ID)\n" +
                "  JOIN ATTRIBUTES CHECK_OUT ON\n" +
                "  (CHECK_OUT.ATTR_ID = 39 AND BOOKING.OBJECT_ID = CHECK_OUT.OBJECT_ID)\n" +
                "  JOIN OBJREFERENCE STATUS_ID ON\n" +
                "  (STATUS_ID.ATTR_ID = 42 AND BOOKING.OBJECT_ID = STATUS_ID.OBJECT_ID)\n" +
                "WHERE STATUS_ID.REFERENCE <> 29 AND STATUS_ID.REFERENCE <> 32 AND " +
                "   TO_DATE(GREATEST(CHECK_IN.DATE_VALUE,?),'dd.mm.yyyy') < TO_DATE(LEAST(CHECK_OUT.DATE_VALUE,?),'dd.mm.yyyy')";
        if (!getJdbcTemplate().queryForList(sql, Long.TYPE, model.getRoomId(), model.getCheckIn(), model.getCheckOut()).isEmpty())
            return false;

        String sqlNewObjId = "select object_id_seq.nextval new_id from dual";
        Long newObjId = getJdbcTemplate().queryForObject(sqlNewObjId, Long.TYPE);

        sql = "INSERT ALL\n" +
                "    INTO objects (object_id, PARENT_ID, object_type_id, name)\n" +
                "        VALUES (?, ?, 7, 'Booking '|| ?) --object_id, room_id, booling_name\n" +
                "    INTO attributes (attr_id, object_id, value)\n" +
                "        VALUES (35, ?, ?) --booking_code\n" +
                "    INTO attributes (attr_id, object_id, value)\n" +
                "        VALUES (36, ?, ?) --message\n" +
                "    INTO OBJREFERENCE (attr_id, reference, object_id)\n" +
                "        VALUES (37, ?, ?) --user_id\n" +
                "    INTO attributes (attr_id, object_id, date_value)\n" +
                "        VALUES (38, ?, TO_DATE(?, 'YYYY\"-\"MM\"-\"DD')) --date_check_in\n" +
                "    INTO attributes (attr_id, object_id, date_value)\n" +
                "        VALUES (39, ?, TO_DATE(?, 'YYYY\"-\"MM\"-\"DD')) --date_check_out\n" +
                "    INTO attributes (attr_id, object_id, value)\n" +
                "        VALUES (40, ?, ?) --num_persons\n" +
                "    INTO attributes (attr_id, object_id, value)\n" +
                "        VALUES (41, ?, ?) --is_paid\n" +
                "    INTO OBJREFERENCE (attr_id, reference, object_id)\n" +
                "        VALUES (42, ?, ?) --status_id\n" +
                "    INTO OBJREFERENCE (attr_id, reference, object_id)\n" +
                "        VALUES (43, ?, ?) --pay_sys_id\n" +
                "    INTO attributes (attr_id, object_id, value)\n" +
                "        VALUES (60, ?, COST) --COST\n" +
                "   SELECT (? - ?) * ROOM_COST.VALUE COST\n" +
                "   FROM ATTRIBUTES ROOM_COST\n" +
                "   WHERE ROOM_COST.OBJECT_ID = ? AND ROOM_COST.ATTR_ID = 33";

        int affrctedRows = getJdbcTemplate().update(sql,
                newObjId, model.getRoomId(), model.getCode(),
                newObjId, model.getCode(),
                newObjId, model.getMessage(),
                model.getUserId(), newObjId,
                newObjId, model.getCheckIn().toString(),
                newObjId, model.getCheckOut().toString(),
                newObjId, model.getNumPersons(),
                newObjId, model.isPaid().toString(),
                model.getStatusId(), newObjId,
                model.getPaySysId(), newObjId,
                newObjId, model.getCheckOut(), model.getCheckIn(), model.getRoomId()
        );

        boolean isSuccess1 = affrctedRows == 11;

        return isSuccess1;
    }

    @Override
    public boolean update(Booking model) {
        String sql1 = "UPDATE objects SET PARENT_ID = ?, NAME = 'Booking '||? WHERE object_type_id = 7 and object_id = ?";
        String sql2 = "UPDATE ATTRIBUTES SET VALUE = ?, DATE_VALUE = TO_DATE(?, 'YYYY\"-\"MM\"-\"DD') WHERE object_id = ? and attr_id = ?";

        boolean isSuccess = (1 == getJdbcTemplate().update(sql1, model.getRoomId(), model.getCode(), model.getId()));

        boolean isSuccess1 = batchInsertObjReferences(37, Arrays.asList(model.getUserId()), model.getId());
        boolean isSuccess2 = batchInsertObjReferences(42, Arrays.asList(model.getStatusId()), model.getId());
        boolean isSuccess3 = batchInsertObjReferences(43, Arrays.asList(model.getPaySysId()), model.getId());

        List<Object[]> arguments = new ArrayList<Object[]>() {
            {
                add(new Object[]{model.getCode(), null, model.getId(), 35});
                add(new Object[]{model.getMessage(), null, model.getId(), 36});
                add(new Object[]{model.getNumPersons(), null, model.getId(), 40});
                add(new Object[]{model.isPaid().toString(), null, model.getId(), 41});
                add(new Object[]{null, model.getCheckIn().toString(), model.getId(), 38});
                add(new Object[]{null, model.getCheckOut().toString(), model.getId(), 39});

            }
        };

        int[] response = getJdbcTemplate().batchUpdate(sql2, arguments);

        boolean isSuccess4 = true;

        for (int i : response) {
            if (i != -2) {
                isSuccess4 = false;
                break;
            }
        }

        return isSuccess && isSuccess1 && isSuccess2 && isSuccess3 && isSuccess4;
    }

    private final class BookingMapper implements RowMapper<Booking> {
        @Override
        public Booking mapRow(ResultSet rs, int rowNum) throws SQLException {
            Booking booking = new Booking();
            booking.setId(rs.getLong("object_id"));
            booking.setRoomId(rs.getLong("room_id"));
            booking.setCode(rs.getString("booking_code"));
            booking.setMessage(rs.getString("message"));
            booking.setUserId(rs.getLong("user_id"));
            booking.setCheckIn(rs.getDate("date_check_in"));
            booking.setCheckOut(rs.getDate("date_check_out"));
            booking.setNumPersons(rs.getInt("num_persons"));
            booking.setPaid(Boolean.parseBoolean(rs.getString("is_paid")));
            booking.setStatusId(rs.getLong("status_id"));
            booking.setPaySysId(rs.getLong("pay_sys_id"));
            booking.setCost(rs.getDouble("cost"));
            return booking;
        }
    }
}
