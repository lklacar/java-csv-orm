package com.lukaklacar.csvorm.entitymanager;

import com.lukaklacar.csvorm.entity.AbstractEntity;

public class EntityManager<T extends AbstractEntity> {
    private Class classDesciptor;

    public EntityManager(Class<T> classDescriptor) {
        this.classDesciptor = classDescriptor;
    }
}
