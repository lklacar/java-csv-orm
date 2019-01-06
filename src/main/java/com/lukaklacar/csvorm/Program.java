package com.lukaklacar.csvorm;

import com.lukaklacar.csvorm.csv.CSVEncoder;
import com.lukaklacar.csvorm.example.Role;
import com.lukaklacar.csvorm.example.User;
import com.lukaklacar.csvorm.example.UserEntityManager;
import lombok.var;

import java.util.Arrays;

public class Program {

    public static void main(String[] args) {

        UserEntityManager userEntityManager = new UserEntityManager();
        CSVEncoder<User> csvEncoder = new CSVEncoder<>();

        User u = new User();
        u.setId(1L);
        u.setName("Name");
        u.setStrings(Arrays.asList("Test 1", "Test 2"));

        Role r = new Role();
        r.setId(2L);
        r.setName("Role");

        u.setRole(r);

        var header = csvEncoder.getHeader(User.class);
        var encoded = csvEncoder.encode(u);

    }
}
