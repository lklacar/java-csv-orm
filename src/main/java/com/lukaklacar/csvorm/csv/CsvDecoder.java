package com.lukaklacar.csvorm.csv;

import com.lukaklacar.csvorm.util.ClassUtil;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;

public class CsvDecoder<T> {

    private String delimiter;
    private Class<T> classDescriptor;
    private ValueMapper valueMapper;

    public CsvDecoder(String delimiter, Class<T> classDescriptor, ValueMapper valueMapper) {
        this.delimiter = delimiter;
        this.classDescriptor = classDescriptor;
        this.valueMapper = valueMapper;
    }

    public T decode(String encodedString, String header) {
        List<String> fieldNames = Arrays.asList(header.split(delimiter));
        List<String> values = Arrays.asList(encodedString.split(delimiter));
        T object = ClassUtil.newInstance(classDescriptor);

        IntStream.range(0, fieldNames.size())
                .forEach(i -> {
                    String fieldName = fieldNames.get(i);
                    String stringValue = values.get(i);
                    Method setter = ClassUtil.getSetterForField(classDescriptor, fieldName);
                    Method getter = ClassUtil.getGetterForField(classDescriptor, fieldName);
                    Class valueType = getter.getReturnType();

                    Object value = null;
                    //TODO: Make this prettier
                    if (Collection.class.isAssignableFrom(valueType)) {
                        Type singleElementType = getter.getGenericReturnType();
                        if (singleElementType instanceof ParameterizedType) {
                            ParameterizedType type = (ParameterizedType) singleElementType;
                            Type[] typeArguments = type.getActualTypeArguments();
                            for (Type typeArgument : typeArguments) {
                                Class typeArgClass = (Class) typeArgument;
                                value = valueMapper.parseCollectionFromString(stringValue, valueType, typeArgClass);
                            }
                        }
                    } else {
                        value = valueMapper.parseFromString(stringValue, valueType);
                    }


                    ClassUtil.invokeMethod(setter, object, value);
                });

        return object;
    }
}
