package com.lukaklacar.csvorm.csv.mappers;

import com.lukaklacar.csvorm.entity.AbstractEntity;
import com.lukaklacar.csvorm.util.ClassUtil;

import java.util.Optional;

public class EntityMapper implements SingleValueMapper<Object> {

    @Override
    public Object parseFromString(String s, Class<?> type) {
        Object value = ClassUtil.newInstance(type);
        ((AbstractEntity) value).setId(Long.parseLong(s));
        return value;
    }

    @Override
    public String convertToString(Object value) {
        return Optional.ofNullable(value).map(val -> ((AbstractEntity) val).getId().toString()).orElse("");
    }
}
