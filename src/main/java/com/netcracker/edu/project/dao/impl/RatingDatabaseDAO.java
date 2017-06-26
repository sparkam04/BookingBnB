package com.netcracker.edu.project.dao.impl;

import com.netcracker.edu.project.dao.RatingDAO;
import com.netcracker.edu.project.dao.StatusDAO;
import com.netcracker.edu.project.model.Rating;
import com.netcracker.edu.project.model.Status;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RatingDatabaseDAO extends AbstractDatabaseDAO<Rating> implements RatingDAO {

    RatingDatabaseDAO() {
        setObjectTypeId(13);
    }

    @Override
    public Rating getById(Long id) {
        Rating rating;
        String sql = "select rating.object_id, rating.PARENT_ID booking_id \n" +
                "    , rating_value.VALUE rating_value\n" +
                "    , rating_comment.VALUE rating_comment\n" +
                "from objects rating\n" +
                "join attributes rating_comment on rating_comment.OBJECT_ID = rating.OBJECT_ID and rating_comment.ATTR_ID = 54\n" +
                "join attributes rating_value on rating_value.OBJECT_ID = rating.OBJECT_ID and rating_value.ATTR_ID = 55\n" +
                "where rating.OBJECT_TYPE_ID = 13\n" +
                "    and rating.OBJECT_ID = ?";

        rating = getJdbcTemplate().queryForObject(sql, new Object[]{id}, new RatingMapper());

        return rating;
    }

    private final class RatingMapper implements RowMapper<Rating> {
        @Override
        public Rating mapRow(ResultSet rs, int rowNum) throws SQLException {
            Rating rating = new Rating();
            rating.setId(rs.getLong("object_id"));
            rating.setBookingId(rs.getLong("booking_id"));
            rating.setValue(rs.getInt("rating_value"));
            rating.setComment(rs.getString("rating_comment"));
            return rating;
        }
    }

    @Override
    public boolean add(Rating model) {
        String sqlNewObjId = "select object_id_seq.nextval new_id from dual";
        Long newObjId = getJdbcTemplate().queryForObject(sqlNewObjId, Long.TYPE);

        String sql = "INSERT ALL\n" +
                "    INTO objects (object_id, PARENT_ID, object_type_id, name)\n" +
                "        VALUES (?, ?, 13, 'Rating '||?) --object_id, rating_name\n" +
                "    INTO attributes (attr_id, object_id, value)\n" +
                "        VALUES (55, ?, ?) --rating_value\n" +
                "    INTO attributes (attr_id, object_id, value)\n" +
                "        VALUES (54, ?, ?) --rating_comment\n" +
                "select * from dual";

        int affrctedRows = getJdbcTemplate().update(sql,
                newObjId, model.getBookingId(), model.getId(),
                newObjId, model.getValue(),
                newObjId, model.getComment()
                );

        return affrctedRows == 3;
    }

    @Override
    public boolean update(Rating model) {
        String sql1 = "UPDATE objects SET PARENT_ID = ? WHERE object_type_id = 13 and object_id = ?";
        String sql2 = "UPDATE ATTRIBUTES SET VALUE = ? WHERE object_id = ? and attr_id = ?";

        boolean isSuccess = (1 == getJdbcTemplate().update(sql1, model.getBookingId(), model.getId()));
        boolean isSuccess1 = (1 == getJdbcTemplate().update(sql2, model.getValue(), model.getId(), 55));
        boolean isSuccess2 = (1 == getJdbcTemplate().update(sql2, model.getComment(), model.getId(), 54));


        return isSuccess && isSuccess1 && isSuccess2;
    }
}
