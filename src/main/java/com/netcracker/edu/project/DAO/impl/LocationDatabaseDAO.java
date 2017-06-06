package com.netcracker.edu.project.DAO.impl;

import com.netcracker.edu.project.DAO.LocationDAO;
import com.netcracker.edu.project.Model.Location;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

@Repository
public class LocationDatabaseDAO extends AbstractDatabaseDAO<Location> implements LocationDAO {
    @Override
    public Collection<Location> getAll() {
        return null;
    }

    @Override
    public Location getById(Long id) {
        String sql = "select location.object_id, location.PARENT_ID city_Id, street_addr.value address, postal_code.VALUE postal_Code, gps_coords.VALUE gps_Coordinates\n" +
                "from OBJECTS location\n" +
                "    join attributes street_addr on street_addr.OBJECT_ID = location.OBJECT_ID and street_addr.ATTR_ID = 5\n" +
                "    join attributes postal_code on postal_code.OBJECT_ID = location.OBJECT_ID and postal_code.ATTR_ID = 6\n" +
                "    join attributes gps_coords on gps_coords.OBJECT_ID = location.OBJECT_ID and gps_coords.ATTR_ID = 7\n" +
                "where\n" +
                "    location.OBJECT_ID = ?";

        return getJdbcTemplate().queryForObject(sql, new Object[]{id},new LocationMapper());
    }

    private final class LocationMapper implements RowMapper<Location> {
        @Override
        public Location mapRow(ResultSet rs, int rowNum) throws SQLException {
            Location location = new Location();
            location.setId(rs.getLong("object_id"));
            location.setCityId(rs.getLong("city_Id"));
            location.setStreetAddress(rs.getString("address"));
            location.setPostalCode(rs.getString("postal_Code"));
            location.setGPSCoords(rs.getString("gps_Coordinates"));
            return location;
        }
    }

    @Override
    public boolean add(Location model) {
        String sql = "INSERT ALL\n" +
                "    INTO objects (object_id, PARENT_ID, object_type_id, name)\n" +
                "    VALUES (object_id_seq.nextval, ?, 3, ?)\n" +
                "                \n" +
                "    INTO attributes (attr_id, object_id, value)\n" +
                "    VALUES (5, object_id_seq.currval, ?)\n" +
                "    \n" +
                "    INTO attributes (attr_id, object_id, value)\n" +
                "    VALUES (6, object_id_seq.currval, ?)\n" +
                "    \n" +
                "    INTO attributes (attr_id, object_id, value)\n" +
                "    VALUES (7, object_id_seq.currval, ?)\n" +
                "select * from dual";
        int affrctedRows = getJdbcTemplate().update(sql, model.getCityId(), model.getStreetAddress(), model.getStreetAddress(), model.getPostalCode(), model.getGPSCoords());

        return affrctedRows == 4;
    }

    @Override
    public boolean update(Location model) {
        String sql1 = "UPDATE objects SET PARENT_ID = ?, NAME = ? WHERE object_id = ?";
        String sql2 = "UPDATE ATTRIBUTES SET VALUE = ? WHERE object_id = ? and attr_id = 5";
        String sql3 = "UPDATE ATTRIBUTES SET VALUE = ? WHERE object_id = ? and attr_id = 6";
        String sql4 = "UPDATE ATTRIBUTES SET VALUE = ? WHERE object_id = ? and attr_id = 7";


        int affectedRows = getJdbcTemplate().update(sql1, model.getCityId(), model.getStreetAddress(), model.getId());
        affectedRows += getJdbcTemplate().update(sql2, model.getStreetAddress(), model.getId());
        affectedRows += getJdbcTemplate().update(sql3, model.getPostalCode(), model.getId());
        affectedRows += getJdbcTemplate().update(sql4, model.getGPSCoords(), model.getId());

        return affectedRows == 4;
    }

}
