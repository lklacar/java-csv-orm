package com.lukaklacar.csvorm.csv.mappers;

public class StringMapper implements Mapper<String> {
    @Override
    public String parseFromString(String s, Class<?> valueType, Class<?> collectionElementType) {
        return s;
    }

    @Override
    public String convertToString(String value) {
        return value;
    }
}
