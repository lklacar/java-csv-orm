package com.lukaklacar.csvorm.util;

import lombok.Builder;
import lombok.Data;

import java.lang.reflect.Field;
import java.util.Collection;

@Builder
@Data
public class EntityDescription {

    private Field idField;
    private Collection<Field> fields;
    private Collection<Field> relationFields;
    private Collection<Field> primaryTypesCollection;
    private Collection<Field> relationsCollection;

}
