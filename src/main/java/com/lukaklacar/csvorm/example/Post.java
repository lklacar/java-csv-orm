package com.lukaklacar.csvorm.example;

import com.lukaklacar.csvorm.annotation.CSVEntity;
import com.lukaklacar.csvorm.annotation.CSVField;
import com.lukaklacar.csvorm.annotation.CSVId;
import lombok.Data;

@Data
@CSVEntity
public class Post {

    @CSVId
    private Long id;

    @CSVField
    private String text;

}
