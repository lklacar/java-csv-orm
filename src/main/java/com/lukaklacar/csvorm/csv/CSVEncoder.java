package com.lukaklacar.csvorm.csv;

import com.lukaklacar.csvorm.util.ClassUtil;

import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.stream.Collectors;

public class CSVEncoder {

    private String delimiter;
    private ValueMapper valueMapper;

    public CSVEncoder(String delimiter, ValueMapper valueMapper) {
        this.delimiter = delimiter;
        this.valueMapper = valueMapper;
    }

    public String encode(Object object) {
        Class classDescriptor = object.getClass();
        return ClassUtil
                .getAllGetters(classDescriptor)
                .stream()
                .sorted(Comparator.comparing(Method::getName))
                .map(getter -> ClassUtil.invokeMethod(getter, object))
                .map(value -> valueMapper.convertToString(value))
                .collect(Collectors.joining(delimiter));
    }

    public String getHeader(Class classDescriptor){
        return ClassUtil.getFieldNames(classDescriptor).stream().sorted().collect(Collectors.joining(delimiter));
    }

}
