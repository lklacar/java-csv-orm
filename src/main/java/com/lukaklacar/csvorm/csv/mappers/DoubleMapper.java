package com.lukaklacar.csvorm.csv.mappers;

import java.util.Optional;

public class DoubleMapper implements SingleValueMapper<Double> {
    @Override
    public Double parseFromString(String s, Class<?> type) {
        return Double.parseDouble(s);
    }

    @Override
    public String convertToString(Double value) {
        return Optional.ofNullable(value).map(Object::toString).orElse("0");
    }
}
