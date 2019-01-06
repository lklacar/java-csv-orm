package com.lukaklacar.csvorm.csv.mappers;

import com.lukaklacar.csvorm.annotation.CSVField;
import com.lukaklacar.csvorm.annotation.CSVId;
import com.lukaklacar.csvorm.csv.ValueMapper;
import com.lukaklacar.csvorm.util.ClassUtil;
import lombok.var;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.stream.Collectors;

public class RelationCollectionMapper implements Mapper<Collection<?>> {

    private ValueMapper valueMapper;

    public RelationCollectionMapper(ValueMapper valueMapper) {
        this.valueMapper = valueMapper;
    }

    @Override
    public String convertToString(Collection<?> value) {
        return value
                .stream()
                .map(val -> {
                    Field valueIdField = ClassUtil.getAnnotatedField(((Object) val).getClass(), CSVId.class).get(0);
                    var idValue = ClassUtil.getFieldValue(valueIdField, val);
                    return valueMapper.mapValue(idValue);
                })
                .collect(Collectors.joining("|"));
    }

    @Override
    public Collection<?> parseFromString(String s, Class<?> type) {
        return null;
    }
}
