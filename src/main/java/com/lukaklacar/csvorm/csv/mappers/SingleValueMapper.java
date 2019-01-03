package com.lukaklacar.csvorm.csv.mappers;

public interface SingleValueMapper<T> {

    T parseFromString(String s, Class<?> type);

    String convertToString(T value);

}
