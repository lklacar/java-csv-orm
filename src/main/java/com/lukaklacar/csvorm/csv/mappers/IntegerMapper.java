package com.lukaklacar.csvorm.csv.mappers;

import java.util.Optional;

public class IntegerMapper implements SingleValueMapper<Integer> {
    @Override
    public Integer parseFromString(String s, Class<?> type) {
        return Integer.parseInt(s);
    }

    @Override
    public String convertToString(Integer value) {
        return Optional.ofNullable(value).map(Object::toString).orElse("0");
    }
}
