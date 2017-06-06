package com.netcracker.edu.project.DAO.impl;

import com.netcracker.edu.project.DAO.EntityDAO;
import com.netcracker.edu.project.Model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

abstract class AbstractDatabaseDAO<T extends Model> implements EntityDAO<T> {

    private JdbcTemplate jdbcTemplate;

    protected JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean remove(Long id) {
        String sql = "delete from objects where object_id = ?";
        int affectedRows = getJdbcTemplate().update(sql, id);

        return affectedRows == 1;
    }

    @Override
    public boolean remove(T model) {
        String sql = "delete from objects where object_id = ?";
        int affectedRows = getJdbcTemplate().update(sql, model.getId());

        return affectedRows == 1;
    }
}
