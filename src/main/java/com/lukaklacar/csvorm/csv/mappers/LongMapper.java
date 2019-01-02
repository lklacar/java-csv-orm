package com.lukaklacar.csvorm.csv.mappers;


import java.util.Optional;

public class LongMapper implements Mapper<Long> {
    @Override
    public Long parseFromString(String s, Class<?> valueType, Class<?> collectionElementType) {
        return Long.parseLong(s);
    }

    @Override
    public String convertToString(Long value) {
        return Optional.ofNullable(value).map(Object::toString).orElse("");
    }
}
