package com.lukaklacar.csvorm.csv.mappers;

import com.lukaklacar.csvorm.csv.ValueMapper;

import java.util.Collection;
import java.util.stream.Collectors;

public class PrimaryValueCollectionMapper implements Mapper<Collection<?>> {

    private static final String DELIMITER = "|";
    private ValueMapper valueMapper;

    public PrimaryValueCollectionMapper(ValueMapper valueMapper) {
        this.valueMapper = valueMapper;
    }

    @Override
    public String convertToString(Collection<?> value) {
        return value
                .stream()
                .map(val -> valueMapper.mapValue(val))
                .collect(Collectors.joining(DELIMITER));
    }

    @Override
    public Collection parseFromString(String s, Class<?> type) {
        return null;
    }
}
