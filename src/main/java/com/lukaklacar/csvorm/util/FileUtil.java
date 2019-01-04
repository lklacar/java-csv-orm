package com.lukaklacar.csvorm.util;


import com.lukaklacar.csvorm.exceptions.CannotCreateDataFileException;
import com.lukaklacar.csvorm.exceptions.CannotReadFileException;
import com.lukaklacar.csvorm.exceptions.CannotWriteToFileException;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileUtil {

    public static File createFileIfNotExist(String path) {
        try {
            File file = new File(path);
            file.createNewFile();
            return file;
        } catch (IOException e) {
            throw new CannotCreateDataFileException();
        }
    }

    public static List<String> readFileLines(File file) {
        try {
            return FileUtils.readLines(file, "UTF-8");
        } catch (IOException e) {
            throw new CannotReadFileException();
        }
    }

    public static void writeFileLines(File file, List<String> lines) {
        try {
            FileUtils.writeLines(file, lines);
        } catch (IOException e) {
            throw new CannotWriteToFileException();
        }
    }
}
