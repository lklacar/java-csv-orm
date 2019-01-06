package com.lukaklacar.csvorm.csv;

import com.lukaklacar.csvorm.csv.mappers.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ValueMapper {

    private static final String DELIMITER = ",";
    private Map<Class, Mapper> valueMappers;
    private Mapper<Collection<?>> primaryTypeCollectionMapper;
    private Mapper<Collection<?>> relationCollectionMapper;

    public ValueMapper() {
        valueMappers = new HashMap<>();
        valueMappers.put(Double.class, new DoubleMapper());
        valueMappers.put(Float.class, new FloatMapper());
        valueMappers.put(Integer.class, new IntegerMapper());
        valueMappers.put(Long.class, new LongMapper());
        valueMappers.put(Short.class, new ShortMapper());
        valueMappers.put(String.class, new StringMapper());
        primaryTypeCollectionMapper = new PrimaryValueCollectionMapper(this);
        relationCollectionMapper = new RelationCollectionMapper(this);
    }

    @SuppressWarnings("unchecked")
    public  String mapValue(Object idValue) {
        return valueMappers.get(idValue.getClass()).convertToString(idValue);
    }

    public String mapPrimaryTypeCollection(Object idValue) {
        return primaryTypeCollectionMapper.convertToString((Collection) idValue);
    }

    public String mapRelationCollection(Object idValue) {
        return relationCollectionMapper.convertToString((Collection) idValue);
    }
}
