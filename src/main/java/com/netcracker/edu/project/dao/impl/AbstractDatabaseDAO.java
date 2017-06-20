package com.netcracker.edu.project.dao.impl;

import com.netcracker.edu.project.dao.EntityDAO;
import com.netcracker.edu.project.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;
import java.sql.ResultSet;
import java.util.*;

abstract class AbstractDatabaseDAO<T extends Model> implements EntityDAO<T>, SQLRequests, ExceptionMessages {
    protected Boolean hasParentId;
    protected Long objectTypeId;
    protected List<Long> valueAttrIds;
    protected List<Long> singleRefAttrIds;
    protected List<Long> multRefAttrIds;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void init() {
        this.objectTypeId = jdbcTemplate.queryForObject(
                SELECT_OBJTYPE_BY_NAME,
                Long.TYPE,
                getNewModel().getClass().getSimpleName());
        this.hasParentId = null != jdbcTemplate.queryForObject(
                SELECT_PARENTYPEID_BY_OBJTYPEID,
                Long.TYPE,
                objectTypeId);
        this.valueAttrIds = jdbcTemplate.queryForList(
                SELECT_ATTRTYPEID_VALUE_BY_OBJTYPEID,
                Long.TYPE,
                objectTypeId);
        this.singleRefAttrIds = jdbcTemplate.queryForList(
                SELECT_ATTRTYPEID_SINGLEREF_BY_OBJTYPEID,
                Long.TYPE,
                objectTypeId);
        this.multRefAttrIds = jdbcTemplate.queryForList(
                SELECT_ATTRTYPEID_MULTLEREF_BY_OBJTYPEID,
                Long.TYPE,
                objectTypeId
        );
    }

    @Override
    public Collection<T> getAll() {
        List<Long> entityIdList = jdbcTemplate.queryForList(
                SELECT_OBJID_BY_OBJTYPEID_REQUEST,
                new Object[]{objectTypeId},
                Long.TYPE);
        List<T> entityList = new LinkedList<>();
        for (Long entityId : entityIdList) {
            entityList.add(getById(entityId));
        }
        return entityList;
    }

    @Override
    public T getById(Long id) {
        T model = getNewModel();
        model.setId(id);
        if (hasParentId)
            setParentId(model,
                    jdbcTemplate.queryForObject(
                            SELECT_PARENTID_BY_OBJECTID,
                            Long.TYPE,
                            id
                    ));

        setValues(model, jdbcTemplate.query(
                SELECT_VALUE_BY_OBJECTID,
                (ResultSet resultSet) -> {
                    String s;
                    List<String> values = new LinkedList<String>();
                    while (resultSet.next()) {
                        s = resultSet.getString(1);
                        //NOT TO PARSE NULL VALUES
                        values.add(s == null ? "" : s);
                    }
                    return values.iterator();
                }
                , id));

        List<Long> singleReferences = new LinkedList<>();
        for (Long attrId : singleRefAttrIds)
            singleReferences.add(
                    jdbcTemplate.queryForObject(
                            SELECT_REFERENCE_BY_OBJID_ATTRID_REQUEST,
                            Long.TYPE,
                            id, attrId));
        setSingleReferences(model, singleReferences.iterator());

        List<List<Long>> multipleReferences = new LinkedList<>();
        for (Long attrId : multRefAttrIds)
            multipleReferences.add(
                    jdbcTemplate.queryForList(
                            SELECT_REFERENCE_BY_OBJID_ATTRID_REQUEST,
                            Long.TYPE,
                            id, attrId));
        setMultipleReferences(model, multipleReferences.iterator());
        return model;
    }

    @Override
    public boolean add(T model) {
        if (model.getId() == null)
            setNewId(model);
        jdbcTemplate.update(
                INSERT_OBJECT_REQUEST,
                model.getId(), getParentId(model), objectTypeId, getName(model));

        List<Object[]> attributeInsertArgs = new LinkedList<>();
        Iterator<String> values = getValues(model);
        for (Long attrId : valueAttrIds)
            attributeInsertArgs.add(new Object[]{attrId, model.getId(), values.next()});
        jdbcTemplate.batchUpdate(
                INSERT_ATTRIBUTE_REQUEST,
                attributeInsertArgs
        );

        Iterator<Long> singleReferenceIterator = getSingleReferences(model);
        for (Long attrId : singleRefAttrIds)
            batchInsertObjReferences(
                    attrId, Arrays.asList(singleReferenceIterator.next()), model.getId());

        Iterator<List<Long>> multipleReferencesIterator = getMultipleReferences(model);
        for (Long attrId : multRefAttrIds)
            batchInsertObjReferences(attrId, multipleReferencesIterator.next(), model.getId());
        return true;
    }

    @Override
    public boolean update(T model) {
        if (model.getId() == null)
            throw new NullPointerException(NULL_MODEL_ID_MESSAGE);

        jdbcTemplate.update(
                UPDATE_OBJECTS,
                getParentId(model), getName(model), objectTypeId, model.getId());

        Iterator<String> valuesIterator = getValues(model);
        for (Long attrId : valueAttrIds)
            jdbcTemplate.update(
                    UPDATE_ATTRIBUTES,
                    new Object[]{valuesIterator.next(), model.getId(), attrId});

        Iterator<Long> singleReferenceIterator = getSingleReferences(model);
        for (Long attrId : singleRefAttrIds)
            batchInsertObjReferences(
                    attrId, Arrays.asList(singleReferenceIterator.next()), model.getId());

        Iterator<List<Long>> multipleReferencesIterator = getMultipleReferences(model);
        for (Long attrId : multRefAttrIds)
            batchInsertObjReferences(
                    attrId, multipleReferencesIterator.next(), model.getId());

        return true;
    }

    @Override
    public boolean remove(Long id) {
        int affectedRows = jdbcTemplate.update(DELETE_OBJ_BY_OBJID_REQUEST, id);
        return affectedRows == 1;
    }

    @Override
    public boolean remove(T model) {
        return remove(model.getId());
    }

    boolean batchInsertObjReferences(Long attributeId, Collection<Long> references, Long objId) {
        jdbcTemplate.update(
                DELETE_OBJREF_BY_ATTRID_OBJID_REQUEST,
                attributeId,
                objId);

        List<Object[]> arguments = new ArrayList<>();
        for (Long reference : references)
            arguments.add(new Object[]{attributeId, reference, objId});

        for (int i : jdbcTemplate.batchUpdate(INSERT_OBJREF_REQUEST, arguments))
            if (i != -2)
                return false;

        return true;
    }

    protected void setNewId(T model) {
        model.setId(jdbcTemplate.queryForObject(NEW_OBJECT_ID, Long.TYPE));
    }

    abstract protected T getNewModel();

    abstract protected String getName(T model);

    protected Long getParentId(T model) {
        return null;
    }

    protected T setParentId(T model, Long parentId) {
        return model;
    }

    abstract protected Iterator<String> getValues(T model);

    abstract protected T setValues(T model, Iterator<String> valuesIterator);

    protected Iterator<Long> getSingleReferences(T model) {
        return null;
    }

    protected T setSingleReferences(T model, Iterator<Long> singldeReferencesIterator) {
        return model;
    }

    protected Iterator<List<Long>> getMultipleReferences(T model) {
        return null;
    }

    protected T setMultipleReferences(T model, Iterator<List<Long>> multipleReferencesIterator) {
        return model;
    }
}
