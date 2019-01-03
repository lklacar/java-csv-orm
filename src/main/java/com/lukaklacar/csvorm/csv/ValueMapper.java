package com.lukaklacar.csvorm.csv;

import com.lukaklacar.csvorm.csv.mappers.*;
import com.lukaklacar.csvorm.entity.AbstractEntity;
import com.lukaklacar.csvorm.exceptions.CannotFindValueMapper;
import com.lukaklacar.csvorm.exceptions.FieldCannotBeNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;

public class ValueMapper {

    private HashMap<Class<?>, SingleValueMapper> mappers;

    public ValueMapper(String collectionDelimiter) {
        mappers = new HashMap<>();
        mappers.put(Long.class, new LongMapper());
        mappers.put(String.class, new StringMapper());
        mappers.put(AbstractEntity.class, new EntityMapper());
        mappers.put(Double.class, new DoubleMapper());
        mappers.put(Float.class, new FloatMapper());
        mappers.put(Integer.class, new IntegerMapper());
        mappers.put(Short.class, new ShortMapper());
        mappers.put(Collection.class, new GenericCollectionMapper(collectionDelimiter, this));
    }

    public Object parseFromString(String stringValue, Class<?> valueType) {
        return mappers
                .keySet()
                .stream()
                .filter(key -> key.isAssignableFrom(valueType))
                .findFirst()
                .map(key -> ((SingleValueMapper<?>) mappers.get(key)).parseFromString(stringValue, valueType))
                .orElseThrow(CannotFindValueMapper::new);
    }

    public String convertToString(Object val) {
        return Optional.ofNullable(val)
                .map(value -> mappers
                        .keySet()
                        .stream()
                        .filter(key -> key.isAssignableFrom(value.getClass()))
                        .findFirst()
                        .map(key -> mappers.get(key).convertToString(value))
                        .orElseThrow(CannotFindValueMapper::new))
                .orElseThrow(FieldCannotBeNull::new);

    }

    public Object parseCollectionFromString(String stringValue, Class<?> valueType, Class<?> typeArgClass) {
        return ((CollectionMapper<?>) mappers.get(Collection.class))
                .parseCollectionFromString(stringValue, valueType, typeArgClass);
    }
}
