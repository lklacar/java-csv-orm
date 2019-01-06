package com.lukaklacar.csvorm.example;

import com.lukaklacar.csvorm.annotation.*;
import lombok.Builder;
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

    @CSVCollectionRel
    private Collection<Post> posts = new ArrayList<>();

    @CSVCollectionField
    private List<String> strings = new ArrayList<>();

}
