package com.netcracker.edu.project.dao.impl;

import com.netcracker.edu.project.dao.UserDAO;
import com.netcracker.edu.project.model.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserDatabaseDAO extends AbstractDatabaseDAO<User> implements UserDAO {

    UserDatabaseDAO() {
        setObjectTypeId(9);
    }

    @Override
    public User getById(Long id) {
        User user;
        String sql = "select user_.object_id, user_.PARENT_ID role_id \n" +
                "    , first_name.VALUE first_name\n" +
                "    , last_name.VALUE last_name\n" +
                "    , phone.VALUE phone\n" +
                "    , email.VALUE email\n" +
                "    , password_.VALUE password_\n" +
                "from objects user_\n" +
                "join attributes first_name on first_name.OBJECT_ID = user_.OBJECT_ID and first_name.ATTR_ID = 45\n" +
                "join attributes last_name on last_name.OBJECT_ID = user_.OBJECT_ID and last_name.ATTR_ID = 46\n" +
                "join attributes phone on phone.OBJECT_ID = user_.OBJECT_ID and phone.ATTR_ID = 47\n" +
                "join attributes email on email.OBJECT_ID = user_.OBJECT_ID and email.ATTR_ID = 48\n" +
                "join attributes password_ on password_.OBJECT_ID = user_.OBJECT_ID and password_.ATTR_ID = 49\n" +
                "where user_.OBJECT_TYPE_ID = 9\n" +
                "    and user_.OBJECT_ID = ?";

        user = getJdbcTemplate().queryForObject(sql, new Object[]{id}, new UserMapper());

        return user;
    }

    public User getByEmail(String email) {
        User user;
        String sql = "select user_.object_id, user_.PARENT_ID role_id \n" +
                "    , first_name.VALUE first_name\n" +
                "    , last_name.VALUE last_name\n" +
                "    , phone.VALUE phone\n" +
                "    , email.VALUE email\n" +
                "    , password_.VALUE password_\n" +
                "from objects user_\n" +
                "join attributes first_name on first_name.OBJECT_ID = user_.OBJECT_ID and first_name.ATTR_ID = 45\n" +
                "join attributes last_name on last_name.OBJECT_ID = user_.OBJECT_ID and last_name.ATTR_ID = 46\n" +
                "join attributes phone on phone.OBJECT_ID = user_.OBJECT_ID and phone.ATTR_ID = 47\n" +
                "join attributes email on email.OBJECT_ID = user_.OBJECT_ID and email.ATTR_ID = 48\n" +
                "join attributes password_ on password_.OBJECT_ID = user_.OBJECT_ID and password_.ATTR_ID = 49\n" +
                "where user_.OBJECT_TYPE_ID = 9\n" +
                "    and email.VALUE = ?";

        user = getJdbcTemplate().queryForObject(sql, new Object[]{email}, new UserMapper());

        return user;
    }

    private final class UserMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getLong("object_id"));
            user.setRoleId(rs.getLong("role_id"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setPhone(rs.getString("phone"));
            user.setEmail(rs.getString("email"));
            user.setPass(rs.getString("password_"));
            return user;
        }
    }

    @Override
    public boolean add(User model) {
        String sqlNewObjId = "select object_id_seq.nextval new_id from dual";
        Long newObjId = getJdbcTemplate().queryForObject(sqlNewObjId, Long.TYPE);

        String sql = "INSERT ALL\n" +
                "    INTO objects (object_id, PARENT_ID, object_type_id, name)\n" +
                "        VALUES (?, ?, 9, 'User '||?) --object_id, role_id, user_name\n" +
                "    INTO attributes (attr_id, object_id, value)\n" +
                "        VALUES (45, ?, ?) --first_name\n" +
                "    INTO attributes (attr_id, object_id, value)\n" +
                "        VALUES (46, ?, ?) --last_name\n" +
                "    INTO attributes (attr_id, object_id, value)\n" +
                "        VALUES (47, ?, ?) --phone\n" +
                "    INTO attributes (attr_id, object_id, value)\n" +
                "        VALUES (48, ?, ?) --email\n" +
                "    INTO attributes (attr_id, object_id, value)\n" +
                "        VALUES (49, ?, ?) --password_\n" +
                "select * from dual";

        int affrctedRows = getJdbcTemplate().update(sql,
                newObjId, model.getRoleId(), model.getLastName(),
                newObjId, model.getFirstName(),
                newObjId, model.getLastName(),
                newObjId, model.getPhone(),
                newObjId, model.getEmail(),
                newObjId, model.getPass()
                );

        return affrctedRows == 6;
    }

    @Override
    public boolean update(User model) {
        String sql1 = "UPDATE objects SET PARENT_ID = ? , NAME = 'User '||? WHERE object_type_id = 9 and object_id = ?";
        String sql2 = "UPDATE ATTRIBUTES SET VALUE = ? WHERE object_id = ? and attr_id = ?";

        return  (1 == getJdbcTemplate().update(sql1, model.getRoleId(), model.getLastName(), model.getId()))
             && (1 == getJdbcTemplate().update(sql2, model.getFirstName(), model.getId(), 45))
             && (1 == getJdbcTemplate().update(sql2, model.getLastName(), model.getId(), 46))
             && (1 == getJdbcTemplate().update(sql2, model.getPhone(), model.getId(), 47))
             && (1 == getJdbcTemplate().update(sql2, model.getEmail(), model.getId(), 48))
             && (1 == getJdbcTemplate().update(sql2, model.getPass(), model.getId(), 49));
    }
}
