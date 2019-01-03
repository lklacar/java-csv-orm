package com.lukaklacar.csvorm.csv.mappers;

public interface CollectionMapper<T> {

    T parseCollectionFromString(String s, Class<?> type, Class<?> collectionElementType);
}
