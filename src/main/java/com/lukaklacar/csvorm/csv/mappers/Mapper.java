package com.lukaklacar.csvorm.csv.mappers;

public interface Mapper<T> {

    T parseFromString(String s, Class<?> type);

    String convertToString(T value);

}
