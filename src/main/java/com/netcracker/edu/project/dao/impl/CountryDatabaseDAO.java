package com.netcracker.edu.project.dao.impl;

import com.netcracker.edu.project.dao.CountryDAO;
import com.netcracker.edu.project.model.Country;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

@Repository
public class CountryDatabaseDAO extends AbstractDatabaseDAO<Country> implements CountryDAO{

    public CountryDatabaseDAO() {
    }

    @Override
    public Collection<Country> getAll() {
        return null;
    }

    @Override
    public Country getById(Long id) {
        String sql = "select country.object_id , country_code.value country_code, country_name.value name  \n" +
                "from OBJECTS country \n" +
                "join attributes country_code on country_code.OBJECT_ID = country.OBJECT_ID and country_code.ATTR_ID = 1\n" +
                "join attributes country_name on country_name.OBJECT_ID = country.OBJECT_ID and country_name.ATTR_ID = 2\n" +
                "where \n" +
                "    country.OBJECT_ID = ?";

        return getJdbcTemplate().queryForObject(sql, new Object[]{id},new CountryMapper());
    }

    private final class CountryMapper implements RowMapper<Country> {
        @Override
        public Country mapRow(ResultSet rs, int rowNum) throws SQLException {
            Country country = new Country();
            country.setId(rs.getLong("object_id"));
            country.setCode(rs.getString("country_code"));
            country.setName(rs.getString("name"));
            return country;
        }
    }

    @Override
    public void add(Country model) {
        String sql = "INSERT ALL\n" +
                "INTO objects (object_id, object_type_id, name) \n" +
                "VALUES (?, 1, ?)\n" +
                "\n" +
                "INTO attributes (attr_id, object_id, value) \n" +
                "VALUES (1, ?, ?)\n" +
                "\n" +
                "INTO attributes (attr_id, object_id, value) \n" +
                "VALUES (2, ?, ?)\n" +
                "SELECT * FROM DUAL";
        getJdbcTemplate().update(sql, model.getId(), model.getName(), model.getId(), model.getCode(),model.getId(), model.getName());
    }

    @Override
    public void update(Country model) {

    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public void remove(Country model) {

    }
}
