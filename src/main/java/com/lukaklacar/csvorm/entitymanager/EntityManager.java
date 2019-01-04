package com.lukaklacar.csvorm.entitymanager;

import com.lukaklacar.csvorm.csv.CSVEncoder;
import com.lukaklacar.csvorm.util.FileUtil;
import lombok.val;

import java.io.File;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class EntityManager<T> {

    private File dataFile;
    private CSVEncoder csvEncoder;

    public EntityManager() {
        csvEncoder = new CSVEncoder<T>();
        initDataFile();
    }

    @SuppressWarnings("unchecked")
    private Class<T> getGenericType() {
        val superClass = (ParameterizedType) getClass().getGenericSuperclass();
        return (Class<T>) superClass.getActualTypeArguments()[0];
    }

    public File createDataFileIfNotExist() {
        return FileUtil.createFileIfNotExist("data/" + getGenericType().getName() + ".csv");
    }

    public void initDataFile() {
        dataFile = createDataFileIfNotExist();
        writeHeader();
    }

    private void writeHeader() {
        List<String> lines = FileUtil.readFileLines(dataFile);
        if (lines.isEmpty()) {
            lines.add(csvEncoder.getHeader(getGenericType()));
            FileUtil.writeFileLines(dataFile, lines);
        }
    }
}
