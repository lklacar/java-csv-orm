package com.lukaklacar.csvorm.entitymanager;

import com.lukaklacar.csvorm.csv.CSVDecoder;
import com.lukaklacar.csvorm.csv.CSVEncoder;
import com.lukaklacar.csvorm.csv.ValueMapper;
import com.lukaklacar.csvorm.entity.AbstractEntity;
import com.lukaklacar.csvorm.util.FileUtil;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class EntityManager<T extends AbstractEntity> {
    private Class classDescriptor;
    private CSVDecoder<T> csvDecoder;
    private CSVEncoder csvEncoder;

    public EntityManager(Class<T> classDescriptor) {
        this.classDescriptor = classDescriptor;
        ValueMapper valueMapper = new ValueMapper("|");
        csvEncoder = new CSVEncoder(",", valueMapper);
        csvDecoder = new CSVDecoder<>(",", classDescriptor, valueMapper);
        ensureFileExists();
    }

    public Collection<T> findAll() {
        List<String> lines = FileUtil.readFileLines(getFile());
        if (lines.size() == 0) {
            return new ArrayList<>();
        }
        String header = lines.get(0);
        lines.remove(0);
        return lines
                .stream()
                .map(line -> csvDecoder.decode(line, header))
                .collect(Collectors.toList());
    }

    public void save(T entity) {
        Collection<T> all = findAll();
        all.add(entity);
        List<String> lines = all
                .stream()
                .map(item -> csvEncoder.encode(item))
                .collect(Collectors.toList());
        FileUtil.writeLinesToFile(getFile(), lines);
    }

    private void ensureFileExists() {
        FileUtil.createFileIfNotExist(getFile());
    }

    private Path getFile() {
        return Paths.get("data/" + classDescriptor.getName() + ".csv");
    }
}
