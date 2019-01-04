package com.lukaklacar.csvorm.csv.mappers;

public class StringMapper implements PrimitiveTypeMapper<String> {
    @Override
    public String parseFromString(String s, Class<?> valueType) {
        return s;
    }

    @Override
    public String convertToString(String value) {
        return value;
    }
}
