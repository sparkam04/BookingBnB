package com.netcracker.edu.project.dao.impl;

import com.netcracker.edu.project.dao.EntityDAO;
import com.netcracker.edu.project.Model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

abstract class AbstractDatabaseDAO<T extends Model> implements EntityDAO<T> {

    private JdbcTemplate jdbcTemplate;

    JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    @Autowired
    void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
