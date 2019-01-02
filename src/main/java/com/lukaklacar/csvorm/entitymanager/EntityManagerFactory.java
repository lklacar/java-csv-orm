package com.lukaklacar.csvorm.entitymanager;

import com.lukaklacar.csvorm.entity.AbstractEntity;

public class EntityManagerFactory {
    public static <T extends AbstractEntity> EntityManager createEntityManagerForClass(Class<T> classDescriptor) {
        return new EntityManager<T>(classDescriptor);
    }
}
