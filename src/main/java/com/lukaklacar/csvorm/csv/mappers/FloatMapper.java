package com.lukaklacar.csvorm.csv.mappers;

import java.util.Optional;

public class FloatMapper implements Mapper<Float> {
    @Override
    public Float parseFromString(String s, Class<?> type) {
        return Float.parseFloat(s);
    }

    @Override
    public String convertToString(Float value) {
        return Optional.ofNullable(value).map(Object::toString).orElse("0");
    }
}
