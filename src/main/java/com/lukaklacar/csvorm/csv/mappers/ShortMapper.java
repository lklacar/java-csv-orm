package com.lukaklacar.csvorm.csv.mappers;

import java.util.Optional;

public class ShortMapper implements PrimitiveTypeMapper<Short> {
    @Override
    public Short parseFromString(String s, Class<?> type) {
        return Short.parseShort(s);
    }

    @Override
    public String convertToString(Short value) {
        return Optional.ofNullable(value).map(Object::toString).orElse("0");
    }
}
