package com.lukaklacar.csvorm.entity;

import java.util.List;

public class ExampleEntity extends AbstractEntity {

    private String name;
    private Long age;
    private ExampleEntity otherEntity;
    private List<ExampleEntity> exampleEntities;


    public List<String> getStrings() {
        return strings;
    }

    public void setStrings(List<String> strings) {
        this.strings = strings;
    }

    public List<ExampleEntity> getExampleEntities() {
        return exampleEntities;
    }

    public void setExampleEntities(List<ExampleEntity> exampleEntities) {
        this.exampleEntities = exampleEntities;
    }

    private List<String> strings;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public ExampleEntity getOtherEntity() {
        return otherEntity;
    }

    public void setOtherEntity(ExampleEntity otherEntity) {
        this.otherEntity = otherEntity;
    }
}
