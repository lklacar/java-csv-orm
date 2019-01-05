package com.lukaklacar.csvorm.example;

import com.lukaklacar.csvorm.annotation.CSVEntity;
import com.lukaklacar.csvorm.annotation.CSVField;
import com.lukaklacar.csvorm.annotation.CSVId;
import com.lukaklacar.csvorm.annotation.CSVRel;
import lombok.Data;

import java.util.Collection;


@CSVEntity
@Data
public class User {

    @CSVId
    private Long id;

    @CSVField
    private String name;

    @CSVRel
    private Role role;

//    @CSVRel
//    private Collection<Post> posts;

}
