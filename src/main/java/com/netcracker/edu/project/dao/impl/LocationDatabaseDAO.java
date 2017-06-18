package com.netcracker.edu.project.dao.impl;

import com.netcracker.edu.project.dao.LocationDAO;
import com.netcracker.edu.project.model.Location;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Repository
public class LocationDatabaseDAO extends AbstractDatabaseDAO<Location> implements LocationDAO {
    @Override
    protected Location getNewModel() {
        return new Location();
    }

    @Override
    protected String getName(Location model) {
        return model.getStreetAddress();
    }

    @Override
    protected Long getParentId(Location model) {
        return model.getCityId();
    }

    @Override
    protected Location setParentId(Location model, Long parentId) {
        model.setCityId(parentId);
        return model;
    }

    @Override
    protected Iterator<String> getValues(Location model) {
        List<String> values = new LinkedList<>();
        values.add(model.getStreetAddress());
        values.add(model.getPostalCode());
        values.add(model.getGPSCoords());
        return values.iterator();
    }

    @Override
    protected Location setValues(Location model, Iterator<String> valuesIterator) {
        model.setStreetAddress(valuesIterator.next());
        model.setPostalCode(valuesIterator.next());
        model.setGPSCoords(valuesIterator.next());
        return model;
    }

    @Override
    protected Iterator<Long> getSingleReferences(Location model) {
        return null;
    }

    @Override
    protected Location setSingleReferences(Location model, Iterator<Long> singldeReferencesIterator) {
        return model;
    }

    @Override
    protected Iterator<List<Long>> getMultipleReferences(Location model) {
        return null;
    }

    @Override
    protected Location setMultipleReferences(Location model, Iterator<List<Long>> multipleReferencesIterator) {
        return model;
    }
/*@Override
    public boolean add(Location model) {
        String sql = "INSERT ALL\n" +
                "    INTO objects (object_id, PARENT_ID, object_type_id, name)\n" +
                "    VALUES (object_id_seq.nextval, ?, 3, ?)\n" +
                "                \n" +
                "    INTO attributes (attr_id, object_id, value)\n" +
                "    VALUES (5, object_id_seq.currval, ?)\n" +
                "    \n" +
                "    INTO attributes (attr_id, object_id, value)\n" +
                "    VALUES (6, object_id_seq.currval, ?)\n" +
                "    \n" +
                "    INTO attributes (attr_id, object_id, value)\n" +
                "    VALUES (7, object_id_seq.currval, ?)\n" +
                "select * from dual";
        int affrctedRows = getJdbcTemplate().update(sql, model.getCityId(), model.getStreetAddress(), model.getStreetAddress(), model.getPostalCode(), model.getGPSCoords());

        return affrctedRows == 4;
    }

    @Override
    public boolean update(Location model) {
        String sql1 = "UPDATE objects SET PARENT_ID = ?, NAME = ? WHERE object_type_id = 3 and object_id = ?";
        String sql2 = "UPDATE ATTRIBUTES SET VALUE = ? WHERE object_id = ? and attr_id = 5";
        String sql3 = "UPDATE ATTRIBUTES SET VALUE = ? WHERE object_id = ? and attr_id = 6";
        String sql4 = "UPDATE ATTRIBUTES SET VALUE = ? WHERE object_id = ? and attr_id = 7";


        int affectedRows = getJdbcTemplate().update(sql1, model.getCityId(), model.getStreetAddress(), model.getId());
        affectedRows += getJdbcTemplate().update(sql2, model.getStreetAddress(), model.getId());
        affectedRows += getJdbcTemplate().update(sql3, model.getPostalCode(), model.getId());
        affectedRows += getJdbcTemplate().update(sql4, model.getGPSCoords(), model.getId());

        return affectedRows == 4;
    }

    @Override
    public Location mapRow(ResultSet rs, int rowNum) throws SQLException {
        Location location = new Location();
        Iterator<Attribute> codes = sqlEntityDescriptor.attributes.iterator();
        location.setId(rs.getLong(codes.next().code));
        location.setCityId(rs.getLong(codes.next().code));
        location.setStreetAddress(rs.getString(codes.next().code));
        location.setPostalCode(rs.getString(codes.next().code));
        location.setGPSCoords(rs.getString(codes.next().code));
        return location;
    }*/
}
