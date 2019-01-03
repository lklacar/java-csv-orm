package com.lukaklacar.csvorm.util;

import com.lukaklacar.csvorm.exceptions.CannotReadDataFile;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class FileUtil {

    public static List<String> readFileLines(Path filePath) {
        try {
            return Files.lines(filePath).collect(Collectors.toList());
        } catch (IOException e) {
            throw new CannotReadDataFile();
        }
    }

    public static void createFileIfNotExist(Path path) {
        try {
            new FileOutputStream(String.valueOf(path), true).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeLinesToFile(Path filePath, List<String> lines) {
        PrintWriter pw;
        try {
            pw = new PrintWriter(new FileWriter(filePath.toString()));
            lines.forEach(pw::write);
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
