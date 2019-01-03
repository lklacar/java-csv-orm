package com.lukaklacar.csvorm.exceptions;

public class CannotReadDataFile extends CSVORMException {
    public CannotReadDataFile() {
    }

    public CannotReadDataFile(String message) {
        super(message);
    }

    public CannotReadDataFile(String message, Throwable cause) {
        super(message, cause);
    }

    public CannotReadDataFile(Throwable cause) {
        super(cause);
    }

    public CannotReadDataFile(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
