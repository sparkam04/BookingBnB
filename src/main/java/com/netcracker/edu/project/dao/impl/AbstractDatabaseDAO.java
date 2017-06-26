package com.netcracker.edu.project.dao.impl;

import com.netcracker.edu.project.dao.EntityDAO;
import com.netcracker.edu.project.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

abstract class AbstractDatabaseDAO<T extends Model> implements EntityDAO<T> {

    private JdbcTemplate jdbcTemplate;

    private int getObjectTypeId() {
        return objectTypeId;
    }

    void setObjectTypeId(int objectTypeId) {
        this.objectTypeId = objectTypeId;
    }

    private int objectTypeId;

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

    @Override
    public Collection<T> getAll() {
        String sql = "select object_id from objects where object_type_id = ?";
        List<Long> entityIdList = getJdbcTemplate().queryForList(sql, new Object[]{getObjectTypeId()}, Long.TYPE);

        return getEntityCollection(entityIdList);
    }

    @Override
    public Collection<T> getByParentId(Long parentId) {
        String sql = "select object_id from objects where object_type_id = ? and parent_id = ?";
        List<Long> entityIdList = getJdbcTemplate().queryForList(sql, new Object[]{getObjectTypeId(), parentId}, Long.TYPE);

        return getEntityCollection(entityIdList);
    }

    Collection<T> getEntityCollection(List<Long> entityIdList) {
        List<T> entityList = new ArrayList<>();
        for (Long entityId : entityIdList) {
            entityList.add(getById(entityId));
        }
        return entityList;
    }

    boolean batchInsertObjReferences(int attrType, Collection<Long> references, Long objId) {
        String sql1 = "delete from OBJREFERENCE where ATTR_ID = ? and OBJECT_ID = ?";
        String sql2 = "INSERT INTO objreference ( attr_id, reference, object_id ) VALUES (?, ?, ?)";

        getJdbcTemplate().update(sql1, attrType, objId);

        List<Object[]> arguments = new ArrayList<>();

        for (Long reference : references) {
            arguments.add(new Object[]{attrType, reference, objId});
        }

        int[] response = getJdbcTemplate().batchUpdate(sql2, arguments);

        boolean isSuccess = true;

        for (int i : response) {
            if (i != -2) {
                isSuccess = false;
                break;
            }
        }
        return isSuccess;
    }
}
