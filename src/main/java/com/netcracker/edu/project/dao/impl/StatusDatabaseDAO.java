package com.netcracker.edu.project.dao.impl;

import com.netcracker.edu.project.dao.StatusDAO;
import com.netcracker.edu.project.model.Status;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class StatusDatabaseDAO extends AbstractDatabaseDAO<Status> implements StatusDAO {

    StatusDatabaseDAO() {
        setObjectTypeId(6);
    }

    @Override
    public Status getById(Long id) {
        Status status;
        String sql = "select status.object_id\n" +
                "    , status_name.VALUE status_name\n" +
                "from objects status\n" +
                "join attributes status_name on status_name.OBJECT_ID = status.OBJECT_ID and status_name.ATTR_ID = 34\n" +
                "where status.OBJECT_TYPE_ID = 6\n" +
                "    and status.OBJECT_ID = ?";

        status = getJdbcTemplate().queryForObject(sql, new Object[]{id}, new StatusMapper());

        return status;
    }

    private final class StatusMapper implements RowMapper<Status> {
        @Override
        public Status mapRow(ResultSet rs, int rowNum) throws SQLException {
            Status status = new Status();
            status.setId(rs.getLong("object_id"));
            status.setName(rs.getString("status_name"));
            return status;
        }
    }

    @Override
    public boolean add(Status model) {
        String sqlNewObjId = "select object_id_seq.nextval new_id from dual";
        Long newObjId = getJdbcTemplate().queryForObject(sqlNewObjId, Long.TYPE);

        String sql = "INSERT ALL\n" +
                "    INTO objects (object_id, PARENT_ID, object_type_id, name)\n" +
                "        VALUES (?, null, 6, 'Status '||?) --object_id, status_name\n" +
                "    INTO attributes (attr_id, object_id, value)\n" +
                "        VALUES (34, ?, ?) --status_name\n" +
                "select * from dual";

        int affrctedRows = getJdbcTemplate().update(sql,
                newObjId, model.getName(),
                newObjId, model.getName()
                );

        return affrctedRows == 2;
    }

    @Override
    public boolean update(Status model) {
        String sql1 = "UPDATE objects SET NAME = 'Status '||? WHERE object_type_id = 6 and object_id = ?";
        String sql2 = "UPDATE ATTRIBUTES SET VALUE = ? WHERE object_id = ? and attr_id = ?";

        boolean isSuccess = (1 == getJdbcTemplate().update(sql1, model.getName(), model.getId()));
        boolean isSuccess1 = (1 == getJdbcTemplate().update(sql2, model.getName(), model.getId(), 34));

        return isSuccess && isSuccess1;
    }
}
