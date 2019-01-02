package com.lukaklacar.csvorm.exceptions;

public class CsvOrmException extends RuntimeException {
    public CsvOrmException() {
    }

    public CsvOrmException(String message) {
        super(message);
    }

    public CsvOrmException(String message, Throwable cause) {
        super(message, cause);
    }

    public CsvOrmException(Throwable cause) {
        super(cause);
    }

    public CsvOrmException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
