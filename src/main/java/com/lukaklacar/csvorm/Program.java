package com.lukaklacar.csvorm;

import com.lukaklacar.csvorm.csv.CSVEncoder;
import com.lukaklacar.csvorm.example.User;
import com.lukaklacar.csvorm.example.UserEntityManager;
import lombok.var;

public class Program {

    public static void main(String[] args) {

        UserEntityManager userEntityManager = new UserEntityManager();
        CSVEncoder<User> csvEncoder = new CSVEncoder<>();

        User u = new User();
        u.setId(1L);
        u.setName("Name");

        var mapped = csvEncoder.encode(u);

    }
}
