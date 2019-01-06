package com.lukaklacar.csvorm.util;

import com.lukaklacar.csvorm.annotation.*;

import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class EntityUtil {

    public static EntityDescription getEntityDescription(Class<?> entity) {
        Field idField = ClassUtil.getAnnotatedField(entity, CSVId.class).get(0);

        List<Field> fields = ClassUtil.getAnnotatedField(entity, CSVField.class)
                .stream()
                .sorted(Comparator.comparing(Field::getName)).collect(Collectors.toList());

        List<Field> relationFields = ClassUtil.getAnnotatedField(entity, CSVRel.class)
                .stream().sorted(Comparator.comparing(Field::getName))
                .collect(Collectors.toList());

        List<Field> primaryTypeCollectionFields = ClassUtil.getAnnotatedField(entity, CSVFieldCollection.class)
                .stream().sorted(Comparator.comparing(Field::getName))
                .collect(Collectors.toList());

        List<Field> relationCollectionFields = ClassUtil.getAnnotatedField(entity, CSVRelCollection.class)
                .stream().sorted(Comparator.comparing(Field::getName))
                .collect(Collectors.toList());

        return EntityDescription.builder()
                .idField(idField)
                .fields(fields)
                .relationFields(relationFields)
                .primaryTypesCollection(primaryTypeCollectionFields)
                .relationsCollection(relationCollectionFields)
                .build();
    }

}
