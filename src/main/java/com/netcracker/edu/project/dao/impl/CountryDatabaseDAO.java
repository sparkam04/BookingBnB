package com.netcracker.edu.project.dao.impl;

import com.netcracker.edu.project.dao.CountryDAO;
import com.netcracker.edu.project.model.Country;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class CountryDatabaseDAO extends AbstractDatabaseDAO<Country> implements CountryDAO{

    public CountryDatabaseDAO() {
        setObjectTypeId(1);
    }

    @Override
    public Country getById(Long id) {
        String sql = "select country.object_id , country_code.value country_code, country_name.value name  \n" +
                "from OBJECTS country \n" +
                "join attributes country_code on country_code.OBJECT_ID = country.OBJECT_ID and country_code.ATTR_ID = 1\n" +
                "join attributes country_name on country_name.OBJECT_ID = country.OBJECT_ID and country_name.ATTR_ID = 2\n" +
                "where \n" +
                "   country.OBJECT_TYPE_ID = 1 " +
                "   and country.OBJECT_ID = ?";

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
    public boolean add(Country country) {
        String sql = "INSERT ALL\n" +
                "INTO objects (object_id, object_type_id, name) \n" +
                "VALUES (object_id_seq.nextval, 1, ?)\n" +
                "\n" +
                "INTO attributes (attr_id, object_id, value) \n" +
                "VALUES (1, object_id_seq.currval, ?)\n" +
                "\n" +
                "INTO attributes (attr_id, object_id, value) \n" +
                "VALUES (2, object_id_seq.currval, ?)\n" +
                "SELECT * FROM DUAL";
        int affrctedRows = getJdbcTemplate().update(sql, country.getName(), country.getCode(), country.getName());

        return affrctedRows == 3;
    }

    @Override
    public boolean update(Country country) {
        String sql1 = "UPDATE objects SET NAME = ? WHERE object_type_id = 1 and object_id = ?";
        String sql2 = "UPDATE ATTRIBUTES SET VALUE = ? WHERE object_id = ? and attr_id = 1";
        String sql3 = "UPDATE ATTRIBUTES SET VALUE = ? WHERE object_id = ? and attr_id = 2";

        int affectedRows = getJdbcTemplate().update(sql1, country.getName(), country.getId());
        affectedRows += getJdbcTemplate().update(sql2, country.getCode(), country.getId());
        affectedRows += getJdbcTemplate().update(sql3, country.getName(), country.getId());

        return affectedRows == 3;
    }
}
