package com.lukaklacar.csvorm;

import com.lukaklacar.csvorm.csv.CSVDecoder;
import com.lukaklacar.csvorm.csv.CSVEncoder;
import com.lukaklacar.csvorm.csv.ValueMapper;
import com.lukaklacar.csvorm.entity.ExampleEntity;

import java.util.Arrays;

public class Program {

    public static void main(String[] args) {

        ExampleEntity exampleEntity1 = new ExampleEntity();
        exampleEntity1.setAge(123L);
        exampleEntity1.setName("test");
        exampleEntity1.setId(321L);
        exampleEntity1.setStrings(Arrays.asList("asd", "dsa"));

        ExampleEntity exampleEntity2 = new ExampleEntity();
        exampleEntity2.setAge(123L);
        exampleEntity2.setName("test");
        exampleEntity2.setId(111L);

        exampleEntity1.setOtherEntity(exampleEntity2);
        exampleEntity1.setExampleEntities(Arrays.asList(exampleEntity2));


        ValueMapper valueMapper = new ValueMapper("|");


        String encodedString = new CSVEncoder(",", valueMapper).encode(exampleEntity1);
        String header = new CSVEncoder(",", valueMapper).getHeader(exampleEntity1.getClass());

        ExampleEntity e = new CSVDecoder<>(",", ExampleEntity.class, valueMapper).decode(encodedString, header);

    }
}
