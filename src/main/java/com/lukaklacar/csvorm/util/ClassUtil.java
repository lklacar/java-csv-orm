package com.lukaklacar.csvorm.util;

import com.lukaklacar.csvorm.exceptions.CannotCreateObjectInstanceException;
import com.lukaklacar.csvorm.exceptions.CannotFieldSetterForField;
import com.lukaklacar.csvorm.exceptions.CannotGetPropertyDescriptors;
import com.lukaklacar.csvorm.exceptions.CannotInvokeMethodException;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ClassUtil {

    public static List<Method> getAllGetters(Class classDescriptor) {
        return getPropertyDescriptors(classDescriptor)
                .stream()
                .map(PropertyDescriptor::getReadMethod)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public static List<Method> getAllSetters(Class classDescriptor) {
        return getPropertyDescriptors(classDescriptor)
                .stream()
                .map(PropertyDescriptor::getWriteMethod)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public static List<String> getFieldNames(Class classDescriptor) {
        return getPropertyDescriptors(classDescriptor)
                .stream()
                .map(PropertyDescriptor::getName)
                .collect(Collectors.toList());
    }

    public static List<PropertyDescriptor> getPropertyDescriptors(Class classDescriptor) {
        try {
            return Arrays.stream(Introspector.getBeanInfo(classDescriptor).getPropertyDescriptors())
                    .filter(Objects::nonNull)
                    .filter(propertyDescriptor -> !propertyDescriptor.getName().equals("class"))
                    .collect(Collectors.toList());
        } catch (IntrospectionException e) {
            throw new CannotGetPropertyDescriptors("Cannot get getter for class {}" + classDescriptor.getName(), e);
        }
    }

    public static Object invokeMethod(Method method, Object object, Object... args) {
        try {
            return method.invoke(object, args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new CannotInvokeMethodException("Cannot invoke method {}" + method.getName(), e);
        }
    }

    public static Method getSetterForField(Class classDescriptor, String fieldName) {
        return getPropertyDescriptors(classDescriptor)
                .stream()
                .filter(propertyDescriptor -> propertyDescriptor.getName().equals(fieldName))
                .findFirst()
                .map(PropertyDescriptor::getWriteMethod)
                .orElseThrow(CannotFieldSetterForField::new);

    }

    public static <T> T newInstance(Class<T> classDescriptor) {
        try {
            return classDescriptor.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new CannotCreateObjectInstanceException();
        }
    }

    public static <T> Method getGetterForField(Class<T> classDescriptor, String fieldName) {
        return getPropertyDescriptors(classDescriptor)
                .stream()
                .filter(propertyDescriptor -> propertyDescriptor.getName().equals(fieldName))
                .findFirst()
                .map(PropertyDescriptor::getReadMethod)
                .orElseThrow(CannotFieldSetterForField::new);
    }

    public static Class getGenericType(Type singleElementType) {
        if (singleElementType instanceof ParameterizedType) {
            ParameterizedType type = (ParameterizedType) singleElementType;
            Type[] typeArguments = type.getActualTypeArguments();
            return (Class) typeArguments[0];
        }
        return null;
    }

}
