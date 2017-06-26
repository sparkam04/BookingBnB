package com.netcracker.edu.project.dao.impl;

import com.netcracker.edu.project.dao.RoleDAO;
import com.netcracker.edu.project.model.Role;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class RoleDatabaseDAO extends AbstractDatabaseDAO<Role> implements RoleDAO {

    RoleDatabaseDAO() {
        setObjectTypeId(8);
    }

    @Override
    public Role getById(Long id) {
        Role role;
        String sql = "select role.object_id\n" +
                "    , role_name.VALUE role_name\n" +
                "from objects role\n" +
                "join attributes role_name on role_name.OBJECT_ID = role.OBJECT_ID and role_name.ATTR_ID = 44\n" +
                "where role.OBJECT_TYPE_ID = 8\n" +
                "    and role.OBJECT_ID = ?";

        role = getJdbcTemplate().queryForObject(sql, new Object[]{id}, new RoleMapper());

        return role;
    }

    private final class RoleMapper implements RowMapper<Role> {
        @Override
        public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
            Role role = new Role();
            role.setId(rs.getLong("object_id"));
            role.setName(rs.getString("role_name"));
            return role;
        }
    }

    @Override
    public boolean add(Role model) {
        String sqlNewObjId = "select object_id_seq.nextval new_id from dual";
        Long newObjId = getJdbcTemplate().queryForObject(sqlNewObjId, Long.TYPE);

        String sql = "INSERT ALL\n" +
                "    INTO objects (object_id, PARENT_ID, object_type_id, name)\n" +
                "        VALUES (?, null, 8, 'Role '||?) --object_id, role_name\n" +
                "    INTO attributes (attr_id, object_id, value)\n" +
                "        VALUES (44, ?, ?) --role_name\n" +
                "select * from dual";

        int affrctedRows = getJdbcTemplate().update(sql,
                newObjId, model.getName(),
                newObjId, model.getName()
                );

        return affrctedRows == 2;
    }

    @Override
    public boolean update(Role model) {
        String sql1 = "UPDATE objects SET NAME = 'Role '||? WHERE object_type_id = 8 and object_id = ?";
        String sql2 = "UPDATE ATTRIBUTES SET VALUE = ? WHERE object_id = ? and attr_id = ?";

        boolean isSuccess = (1 == getJdbcTemplate().update(sql1, model.getName(), model.getId()));
        boolean isSuccess1 = (1 == getJdbcTemplate().update(sql2, model.getName(), model.getId(), 44));

        return isSuccess && isSuccess1;
    }
}
