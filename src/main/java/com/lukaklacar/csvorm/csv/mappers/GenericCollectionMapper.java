package com.lukaklacar.csvorm.csv.mappers;

import com.lukaklacar.csvorm.csv.ValueMapper;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Arrays;
import java.util.Collection;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class GenericCollectionMapper implements SingleValueMapper<Collection<?>>, CollectionMapper<Collection<?>> {
    private final String collectionDelimiter;
    private ValueMapper valueMapper;

    public GenericCollectionMapper(String collectionDelimiter, ValueMapper valueMapper) {
        this.collectionDelimiter = collectionDelimiter;
        this.valueMapper = valueMapper;
    }

    @Override
    public Collection<?> parseFromString(String s, Class<?> type) {
        throw new NotImplementedException();
    }

    @Override
    public String convertToString(Collection<?> value) {
        return value
                .stream()
                .map(val -> valueMapper.convertToString(val))
                .collect(Collectors.joining(collectionDelimiter));
    }

    @Override
    public Collection<?> parseCollectionFromString(String s, Class<?> type, Class<?> collectionElementType) {
        return Arrays.stream(s.split(Pattern.quote(collectionDelimiter)))
                .map(value -> valueMapper.parseFromString(value, collectionElementType))
                .collect(Collectors.toList());
    }
}
