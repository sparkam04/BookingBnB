package com.netcracker.edu.project.dao.impl;

import com.netcracker.edu.project.dao.EntityDAO;
import com.netcracker.edu.project.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;
import java.util.*;

abstract class AbstractDatabaseDAO<T extends Model> implements EntityDAO<T>, SQLRequests {
    private static final Map<Class, String> objTypes = new HashMap<Class, String>() {{
        put(CountryDatabaseDAO.class, Country.class.getSimpleName());
        put(CityDatabaseDAO.class, City.class.getSimpleName());
        put(LocationDatabaseDAO.class, Location.class.getSimpleName());
        put(HotelDatabaseDAO.class, Hotel.class.getSimpleName());
        put(RoomDatabaseDAO.class, Room.class.getSimpleName());
    }};

    protected Boolean hasParentId;
    protected Long objectTypeId;
    protected List<Long> valueAttrIds;
    protected List<Long> singleRefAttrIds;
    protected List<Long> multRefAttrIds;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    protected JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    private void init() {
        this.objectTypeId = getJdbcTemplate().queryForObject(
                SELECT_OBJTYPE_BY_NAME,
                Long.TYPE,
                objTypes.get(this.getClass()));
        this.hasParentId = null != getJdbcTemplate().queryForObject(
                SELECT_PARENTYPEID_BY_OBJTYPEID,
                Long.TYPE,
                objectTypeId);
        this.valueAttrIds = getJdbcTemplate().queryForList(
                SELECT_ATTRTYPEID_VALUE_BY_OBJTYPEID,
                Long.TYPE,
                objectTypeId);
        this.singleRefAttrIds = getJdbcTemplate().queryForList(
                SELECT_ATTRTYPEID_SINGLEREF_BY_OBJTYPEID,
                Long.TYPE,
                objectTypeId);
        this.multRefAttrIds = getJdbcTemplate().queryForList(
                SELECT_ATTRTYPEID_MULTLEREF_BY_OBJTYPEID,
                Long.TYPE,
                objectTypeId
        );
        return;
    }

    @Override
    public Collection<T> getAll() {
        List<Long> entityIdList = getJdbcTemplate().queryForList(
                SELECT_OBJID_BY_OBJTYPEID_REQUEST,
                new Object[]{objectTypeId},
                Long.TYPE);

        List<T> entityList = new ArrayList<>();
        for (Long entityId : entityIdList)
            entityList.add(getById(entityId));

        return entityList;
    }

    @Override
    public T getById(Long id) {
        T model = getNewModel();
        model.setId(id);
        if (hasParentId)
            setParentId(model,
                    getJdbcTemplate().queryForObject(
                            SELECT_PARENTID_BY_OBJECTID,
                            Long.TYPE,
                            id
                    ));
        setValues(model,
                getJdbcTemplate().queryForList(
                        SELECT_VALUE_BY_OBJECTID,
                        String.class,
                        id
                ).iterator());

        List<Long> singleReferences = new LinkedList<>();
        for (Long attrId : singleRefAttrIds)
            singleReferences.add(
                    getJdbcTemplate().queryForObject(
                            SELECT_REFERENCE_BY_OBJID_ATTRID_REQUEST,
                            Long.TYPE,
                            id, attrId));
        setSingleReferences(model, singleReferences.iterator());

        List<List<Long>> multipleReferences = new LinkedList<>();
        for (Long attrId : multRefAttrIds)
            multipleReferences.add(
                    getJdbcTemplate().queryForList(
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
        getJdbcTemplate().update(
                INSERT_OBJECT_REQUEST,
                model.getId(), getParentId(model), objectTypeId, getName(model));

        List<Object[]> attributeInsertArgs = new LinkedList<>();
        Iterator<String> values = getValues(model);
        for (Long attrId : valueAttrIds)
            attributeInsertArgs.add(new Object[]{attrId, model.getId(), values.next()});
        getJdbcTemplate().batchUpdate(
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
            throw new NullPointerException("ID CANT BE NULL");

        getJdbcTemplate().update(
                UPDATE_OBJECTS,
                getParentId(model), getName(model), objectTypeId, model.getId());

        Iterator<String> valuesIterator = getValues(model);
        for (Long attrId : valueAttrIds)
            getJdbcTemplate().update(
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
        int affectedRows = getJdbcTemplate().update(DELETE_OBJ_BY_OBJID_REQUEST, id);
        return affectedRows == 1;
    }

    @Override
    public boolean remove(T model) {
        return remove(model.getId());
    }

    boolean batchInsertObjReferences(Long attributeId, Collection<Long> references, Long objId) {
        getJdbcTemplate().update(
                DELETE_OBJREF_BY_ATTRID_OBJID_REQUEST,
                attributeId,
                objId);

        List<Object[]> arguments = new ArrayList<>();
        for (Long reference : references)
            arguments.add(new Object[]{attributeId, reference, objId});

        for (int i : getJdbcTemplate().batchUpdate(INSERT_OBJREF_REQUEST, arguments))
            if (i != -2)
                return false;

        return true;
    }

    protected void setNewId(T model) {
        model.setId(getJdbcTemplate().queryForObject(NEW_OBJECT_ID, Long.TYPE));
    }

    abstract protected T getNewModel();

    abstract protected String getName(T model);

    abstract protected Long getParentId(T model);

    abstract protected T setParentId(T model, Long parentId);

    abstract protected Iterator<String> getValues(T model);

    abstract protected T setValues(T model, Iterator<String> valuesIterator);

    abstract protected Iterator<Long> getSingleReferences(T model);

    abstract protected T setSingleReferences(T model, Iterator<Long> singldeReferencesIterator);

    abstract protected Iterator<List<Long>> getMultipleReferences(T model);

    abstract protected T setMultipleReferences(T model, Iterator<List<Long>> multipleReferencesIterator);
}
