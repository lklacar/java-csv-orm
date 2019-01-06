package com.lukaklacar.csvorm.example;

import com.lukaklacar.csvorm.annotation.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@CSVEntity
@Data
public class User {

    @CSVId
    private Long id;

    @CSVField
    private String name;

    @CSVRel
    private Role role;

    @CSVRelCollection
    private Collection<Post> posts = new ArrayList<>();

    @CSVFieldCollection
    private List<String> strings = new ArrayList<>();

}
