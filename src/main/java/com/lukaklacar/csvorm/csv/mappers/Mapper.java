package com.lukaklacar.csvorm.csv.mappers;

public interface Mapper<T> {

    T parseFromString(String s, Class<?> type, Class<?> collectionElementType);

    String convertToString(T value);

}
