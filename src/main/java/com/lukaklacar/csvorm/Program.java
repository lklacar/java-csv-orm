package com.lukaklacar.csvorm;

import com.lukaklacar.csvorm.csv.CsvDecoder;
import com.lukaklacar.csvorm.csv.CsvEncoder;
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


        String encodedString = new CsvEncoder(",", valueMapper).encode(exampleEntity1);
        String header = new CsvEncoder(",", valueMapper).getHeader(exampleEntity1.getClass());

        ExampleEntity e = new CsvDecoder<>(",", ExampleEntity.class, valueMapper).decode(encodedString, header);

    }
}
