package com.lukaklacar.csvorm.csv;

import com.lukaklacar.csvorm.annotation.CSVId;
import com.lukaklacar.csvorm.util.ClassUtil;
import com.lukaklacar.csvorm.util.EntityDescription;
import com.lukaklacar.csvorm.util.EntityUtil;
import lombok.var;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.stream.Collectors;

public class CSVEncoder<T> {

    private static final String DELIMITER = ",";
    private Mapper valueMapper;

    public CSVEncoder() {
        valueMapper = new Mapper();
    }

    public String encode(T entity) {
        EntityDescription entityDescription = EntityUtil.getEntityDescription(entity.getClass());

        return String.join(DELIMITER,
                getIdValue(entity, entityDescription),
                getPrimaryTypeValues(entity, entityDescription),
                getRelationValues(entity, entityDescription),
                getPrimaryTypeCollectionValues(entity, entityDescription)
        );
    }

    private String getPrimaryTypeCollectionValues(T entity, EntityDescription entityDescription) {
        return entityDescription.getPrimaryTypesCollection()
                .stream()
                .map(field ->
                {
                    var collection = ClassUtil.getFieldValue(field, entity);
                    return valueMapper.mapCollection((Collection) collection);
                })
                .collect(Collectors.joining(DELIMITER));
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
                    return valueMapper.mapValue(relatedObjectIdValue);
                })
                .collect(Collectors.joining(DELIMITER));
    }

    private String getPrimaryTypeValues(T entity, EntityDescription entityDescription) {
        return entityDescription.getFields()
                .stream()
                .map(field -> valueMapper.mapValue(ClassUtil.getFieldValue(field, entity)))
                .collect(Collectors.joining(DELIMITER));
    }

    private String getIdValue(T entity, EntityDescription entityDescription) {
        return valueMapper.mapValue(ClassUtil.getFieldValue(entityDescription.getIdField(), entity));
    }

    public String getHeader(Class<?> classDescriptor) {
        EntityDescription entityDescription = EntityUtil.getEntityDescription(classDescriptor);

        return String.join(DELIMITER,
                entityDescription.getIdField().getName(),
                entityDescription.getFields().stream().map(Field::getName).collect(Collectors.joining(DELIMITER)),
                entityDescription.getRelationFields().stream().map(Field::getName).collect(Collectors.joining(DELIMITER)),
                entityDescription.getPrimaryTypesCollection().stream().map(Field::getName).collect(Collectors.joining(DELIMITER))
        );
    }

}
