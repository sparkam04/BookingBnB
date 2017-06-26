package com.netcracker.edu.project.dao.impl;

import com.netcracker.edu.project.dao.ImageDAO;
import com.netcracker.edu.project.model.Image;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ImageDatabaseDAO extends AbstractDatabaseDAO<Image> implements ImageDAO {

    ImageDatabaseDAO() {
        setObjectTypeId(11);
    }

    @Override
    public Image getById(Long id) {
        Image image;
        String sql = "select image.object_id\n" +
                "    , image_url.VALUE image_url\n" +
                "    , image_description.VALUE image_description\n" +
                "from objects image\n" +
                "join attributes image_url on image_url.OBJECT_ID = image.OBJECT_ID and image_url.ATTR_ID = 52\n" +
                "join attributes image_description on image_description.OBJECT_ID = image.OBJECT_ID and image_description.ATTR_ID = 53\n" +
                "where image.OBJECT_TYPE_ID = 11\n" +
                "    and image.OBJECT_ID = ?";

        image = getJdbcTemplate().queryForObject(sql, new Object[]{id}, new ImageMapper());

        return image;
    }

    private final class ImageMapper implements RowMapper<Image> {
        @Override
        public Image mapRow(ResultSet rs, int rowNum) throws SQLException {
            Image image = new Image();
            image.setId(rs.getLong("object_id"));
            image.setImageUrl(rs.getString("image_url"));
            image.setImageDescription(rs.getString("image_description"));
            return image;
        }
    }

    @Override
    public boolean add(Image model) {
        String sqlNewObjId = "select object_id_seq.nextval new_id from dual";
        Long newObjId = getJdbcTemplate().queryForObject(sqlNewObjId, Long.TYPE);

        String sql = "INSERT ALL\n" +
                "    INTO objects (object_id, PARENT_ID, object_type_id, name)\n" +
                "        VALUES (?, null, 11, ?) --object_id, image_descr\n" +
                "    INTO attributes (attr_id, object_id, value)\n" +
                "        VALUES (52, ?, ?) --image_url\n" +
                "    INTO attributes (attr_id, object_id, value)\n" +
                "        VALUES (53, ?, ?) --image_description\n" +
                "select * from dual";

        int affrctedRows = getJdbcTemplate().update(sql,
                newObjId, model.getImageDescription(),
                newObjId, model.getImageUrl(),
                newObjId, model.getImageDescription()
                );

        return affrctedRows == 3;
    }

    @Override
    public boolean update(Image model) {
        String sql1 = "UPDATE objects SET NAME = 'Image '||? WHERE object_type_id = 11 and object_id = ?";
        String sql2 = "UPDATE ATTRIBUTES SET VALUE = ? WHERE object_id = ? and attr_id = ?";

        boolean isSuccess = (1 == getJdbcTemplate().update(sql1, model.getImageDescription(), model.getId()));

        List<Object[]> arguments = new ArrayList<Object[]>() {
            {
                add(new Object[]{model.getImageUrl(), model.getId(), 52});
                add(new Object[]{model.getImageDescription(), model.getId(), 53});
            }
        };

        int[] response = getJdbcTemplate().batchUpdate(sql2, arguments);

        boolean isSuccess1 = true;

        for (int i : response) {
            if (i != -2) {
                isSuccess1 = false;
                break;
            }
        }

        return isSuccess && isSuccess1;
    }
}
