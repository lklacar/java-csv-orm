package com.lukaklacar.csvorm.csv;

import com.lukaklacar.csvorm.csv.mappers.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Mapper {

    private static final String DELIMITER = ",";
    private Map<Class, PrimitiveTypeMapper> valueMappers;
    private PrimitiveTypeMapper<Collection<?>> collectionMapper;

    public Mapper() {
        valueMappers = new HashMap<>();
        valueMappers.put(Double.class, new DoubleMapper());
        valueMappers.put(Float.class, new FloatMapper());
        valueMappers.put(Integer.class, new IntegerMapper());
        valueMappers.put(Long.class, new LongMapper());
        valueMappers.put(Short.class, new ShortMapper());
        valueMappers.put(String.class, new StringMapper());
        collectionMapper = new PrimaryValueCollectionMapper(this);
    }

    @SuppressWarnings("unchecked")
    public  String mapValue(Object idValue) {
        return valueMappers.get(idValue.getClass()).convertToString(idValue);
    }

    public String mapCollection(Object idValue) {
        return collectionMapper.convertToString((Collection) idValue);
    }

}
