package com.netcracker.edu.project.dao.impl;

import com.netcracker.edu.project.dao.PaySystemDAO;
import com.netcracker.edu.project.dao.StatusDAO;
import com.netcracker.edu.project.model.PaySystem;
import com.netcracker.edu.project.model.Status;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class PaySystemDatabaseDAO extends AbstractDatabaseDAO<PaySystem> implements PaySystemDAO {

    PaySystemDatabaseDAO() {
        setObjectTypeId(10);
    }

    @Override
    public PaySystem getById(Long id) {
        PaySystem paySystem;
        String sql = "select pay_sys.object_id\n" +
                "    , pay_sys_name.VALUE pay_sys_name\n" +
                "from objects pay_sys\n" +
                "join attributes pay_sys_name on pay_sys_name.OBJECT_ID = pay_sys.OBJECT_ID and pay_sys_name.ATTR_ID = 51\n" +
                "where pay_sys.OBJECT_TYPE_ID = 10\n" +
                "    and pay_sys.OBJECT_ID = ?";

        paySystem = getJdbcTemplate().queryForObject(sql, new Object[]{id}, new PaySystemMapper());

        return paySystem;
    }

    private final class PaySystemMapper implements RowMapper<PaySystem> {
        @Override
        public PaySystem mapRow(ResultSet rs, int rowNum) throws SQLException {
            PaySystem paySystem = new PaySystem();
            paySystem.setId(rs.getLong("object_id"));
            paySystem.setName(rs.getString("pay_sys_name"));
            return paySystem;
        }
    }

    @Override
    public boolean add(PaySystem model) {
        String sqlNewObjId = "select object_id_seq.nextval new_id from dual";
        Long newObjId = getJdbcTemplate().queryForObject(sqlNewObjId, Long.TYPE);

        String sql = "INSERT ALL\n" +
                "    INTO objects (object_id, PARENT_ID, object_type_id, name)\n" +
                "        VALUES (?, null, 10, 'Pay System '||?) --object_id, pay_sys_name\n" +
                "    INTO attributes (attr_id, object_id, value)\n" +
                "        VALUES (51, ?, ?) --pay_sys_name\n" +
                "select * from dual";

        int affrctedRows = getJdbcTemplate().update(sql,
                newObjId, model.getName(),
                newObjId, model.getName()
                );

        return affrctedRows == 2;
    }

    @Override
    public boolean update(PaySystem model) {
        String sql1 = "UPDATE objects SET NAME = 'Pay System '||? WHERE object_type_id = 10 and object_id = ?";
        String sql2 = "UPDATE ATTRIBUTES SET VALUE = ? WHERE object_id = ? and attr_id = ?";

        boolean isSuccess = (1 == getJdbcTemplate().update(sql1, model.getName(), model.getId()));
        boolean isSuccess1 = (1 == getJdbcTemplate().update(sql2, model.getName(), model.getId(), 51));

        return isSuccess && isSuccess1;
    }
}
