package com.netcracker.edu.project.dao.impl;

import com.netcracker.edu.project.dao.CityDAO;
import com.netcracker.edu.project.model.City;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class CityDatabaseDAO extends AbstractDatabaseDAO<City> implements CityDAO{

    public CityDatabaseDAO() {
        setObjectTypeId(2);
    }

    @Override
    public City getById(Long id) {
        String sql = "select city.object_id, city.PARENT_ID countryId, city_name.value name\n" +
                "from OBJECTS city\n" +
                "    join attributes city_name on city_name.OBJECT_ID = city.OBJECT_ID and city_name.ATTR_ID = 3\n" +
                "where\n" +
                "   city.object_type_id = 2 " +
                "   and city.OBJECT_ID = ?";

        return getJdbcTemplate().queryForObject(sql, new Object[]{id},new CityMapper());
    }

    private final class CityMapper implements RowMapper<City> {
        @Override
        public City mapRow(ResultSet rs, int rowNum) throws SQLException {
            City city = new City();
            city.setId(rs.getLong("object_id"));
            city.setCountryId(rs.getLong("countryId"));
            city.setName(rs.getString("name"));
            return city;
        }
    }

    @Override
    public boolean add(City city) {
        String sql = "INSERT ALL " +
                "INTO objects (object_id, PARENT_ID, object_type_id, name)" +
                "VALUES (object_id_seq.nextval, ?, 2, ?)" +
                "INTO attributes (attr_id, object_id, value)" +
                "VALUES (3, object_id_seq.currval, ?)" +
                " select * from dual";
        int affrctedRows = getJdbcTemplate().update(sql, city.getCountryId(), city.getName(), city.getName());

        return affrctedRows == 2;
    }

    @Override
    public boolean update(City city) {
        String sql1 = "UPDATE objects SET PARENT_ID = ?, NAME = ? WHERE object_type_id = 2 and object_id = ?";
        String sql2 = "UPDATE ATTRIBUTES SET VALUE = ? WHERE object_id = ? and attr_id = 3";
        int affectedRows = getJdbcTemplate().update(sql1, city.getCountryId(), city.getName(), city.getId());
        affectedRows += getJdbcTemplate().update(sql2, city.getName(), city.getId());

        return affectedRows == 2;
    }
}
