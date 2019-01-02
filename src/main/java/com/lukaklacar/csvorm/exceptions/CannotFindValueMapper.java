package com.lukaklacar.csvorm.exceptions;

public class CannotFindValueMapper extends CsvOrmException {
    public CannotFindValueMapper() {
    }

    public CannotFindValueMapper(String message) {
        super(message);
    }

    public CannotFindValueMapper(String message, Throwable cause) {
        super(message, cause);
    }

    public CannotFindValueMapper(Throwable cause) {
        super(cause);
    }

    public CannotFindValueMapper(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
