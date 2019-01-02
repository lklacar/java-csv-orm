package com.lukaklacar.csvorm.csv.mappers;

import com.lukaklacar.csvorm.csv.ValueMapper;

import java.util.Arrays;
import java.util.Collection;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CollectionMapper implements Mapper<Collection<?>> {
    private final String collectionDelimiter;
    private ValueMapper valueMapper;

    public CollectionMapper(String collectionDelimiter, ValueMapper valueMapper) {
        this.collectionDelimiter = collectionDelimiter;
        this.valueMapper = valueMapper;
    }

    @Override
    public Collection<?> parseFromString(String s, Class<?> type, Class<?> collectionElementType) {
        return Arrays.stream(s.split(Pattern.quote(collectionDelimiter)))
                .map(value -> valueMapper.parseFromString(value, collectionElementType))
                .collect(Collectors.toList());
    }

    @Override
    public String convertToString(Collection<?> value) {
        return value
                .stream()
                .map(val -> valueMapper.convertToString(val))
                .collect(Collectors.joining(collectionDelimiter));
    }
}
