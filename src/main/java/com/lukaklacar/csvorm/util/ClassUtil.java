package com.lukaklacar.csvorm.util;

import com.lukaklacar.csvorm.exceptions.CannotGetFieldValueException;
import lombok.var;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;

public class ClassUtil {

    public static List<Field> getAnnotatedField(Class<?> classDescriptor, Class<? extends Annotation> annotation) {
        return FieldUtils.getFieldsListWithAnnotation(classDescriptor, annotation);
    }

    public static Object getFieldValue(Field f, Object o) {
        try {
            boolean isAccessible = f.isAccessible();
            f.setAccessible(true);
            var value = f.get(o);
            f.setAccessible(isAccessible);
            return value;

        } catch (IllegalAccessException e) {
            throw new CannotGetFieldValueException();
        }
    }

}