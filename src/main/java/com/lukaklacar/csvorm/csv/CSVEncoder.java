package com.lukaklacar.csvorm.csv;

import com.lukaklacar.csvorm.annotation.CSVId;
import com.lukaklacar.csvorm.csv.mappers.*;
import com.lukaklacar.csvorm.util.ClassUtil;
import com.lukaklacar.csvorm.util.EntityDescription;
import com.lukaklacar.csvorm.util.EntityUtil;
import lombok.var;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CSVEncoder<T> {

    private static final String DELIMITER = ",";
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

        return String.join(DELIMITER,
                getIdValue(entity, entityDescription),
                getPrimaryTypeValues(entity, entityDescription),
                getRelationValues(entity, entityDescription)
        );
    }

    private String getRelationValues(T entity, EntityDescription entityDescription) {
        return entityDescription
                .getRelationFields()
                .stream()
                .map(field -> {
                    // TODO: Checks
                    var relatedObject = ClassUtil.getFieldValue(field, entity);
                    var relatedObjectType = relatedObject.getClass();
                    var relatedObjectIdField = ClassUtil.getAnnotatedField(relatedObjectType, CSVId.class).get(0);
                    var relatedObjectIdValue = ClassUtil.getFieldValue(relatedObjectIdField, relatedObject);
                    return mapValue(relatedObjectIdValue);
                })
                .collect(Collectors.joining(DELIMITER));
    }

    private String getPrimaryTypeValues(T entity, EntityDescription entityDescription) {
        return entityDescription.getFields()
                .stream()
                .map(field -> mapValue(ClassUtil.getFieldValue(field, entity)))
                .collect(Collectors.joining(DELIMITER));
    }

    private String getIdValue(T entity, EntityDescription entityDescription) {
        return mapValue(ClassUtil.getFieldValue(entityDescription.getIdField(), entity));
    }

    @SuppressWarnings("unchecked")
    private String mapValue(Object idValue) {
        return valueMappers.get(idValue.getClass()).convertToString(idValue);
    }

    public String getHeader(Class<?> classDescriptor) {
        EntityDescription entityDescription = EntityUtil.getEntityDescription(classDescriptor);

        return String.join(DELIMITER,
                entityDescription.getIdField().getName(),
                entityDescription.getFields().stream().map(Field::getName).collect(Collectors.joining(DELIMITER)),
                entityDescription.getRelationFields().stream().map(Field::getName).collect(Collectors.joining(DELIMITER))
        );
    }

}
