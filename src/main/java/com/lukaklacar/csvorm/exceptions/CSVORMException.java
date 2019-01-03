package com.lukaklacar.csvorm.exceptions;

public class CSVORMException extends RuntimeException {
    public CSVORMException() {
    }

    public CSVORMException(String message) {
        super(message);
    }

    public CSVORMException(String message, Throwable cause) {
        super(message, cause);
    }

    public CSVORMException(Throwable cause) {
        super(cause);
    }

    public CSVORMException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
