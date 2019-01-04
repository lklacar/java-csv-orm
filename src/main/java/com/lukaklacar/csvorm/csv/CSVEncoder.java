package com.lukaklacar.csvorm.csv;

import com.lukaklacar.csvorm.csv.mappers.*;
import com.lukaklacar.csvorm.util.ClassUtil;
import com.lukaklacar.csvorm.util.EntityDescription;
import com.lukaklacar.csvorm.util.EntityUtil;
import lombok.var;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CSVEncoder<T> {

    private Map<Class, PrimitiveTypeMapper> valueMappers;

    public CSVEncoder() {
        valueMappers = new HashMap<>();
        valueMappers.put(Double.class, new DoubleMapper());
        valueMappers.put(Float.class, new FloatMapper());
        valueMappers.put(Integer.class, new IntegerMapper());
        valueMappers.put(Long.class, new LongMapper());
        valueMappers.put(Short.class, new ShortMapper());
        valueMappers.put(String.class, new StringMapper());
    }

    public String encode(T entity) {
        EntityDescription entityDescription = EntityUtil.getEntityDescription(entity.getClass());
        var idValue = ClassUtil.getFieldValue(entityDescription.getIdField(), entity);

        return String.join(",",
                valueMappers.get(idValue.getClass()).convertToString(idValue),
                entityDescription.getFields()
                        .stream()
                        .map(field -> {
                            var value = ClassUtil.getFieldValue(field, entity);
                            return valueMappers.get(value.getClass()).convertToString(ClassUtil.getFieldValue(field, entity));
                        })
                        .collect(Collectors.joining(",")),

                entityDescription.getRelationFields().stream().map(Field::getName).collect(Collectors.joining(",")));
    }

    public String getHeader(Class<?> classDescriptor) {
        EntityDescription entityDescription = EntityUtil.getEntityDescription(classDescriptor);

        return String.join(",",
                entityDescription.getIdField().getName(),
                entityDescription.getFields().stream().map(Field::getName).collect(Collectors.joining(",")),
                entityDescription.getRelationFields().stream().map(Field::getName).collect(Collectors.joining(","))
        );
    }

}
