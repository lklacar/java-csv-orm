package com.lukaklacar.csvorm.exceptions;

public class FieldCannotBeNull extends CSVORMException {
    public FieldCannotBeNull() {
    }

    public FieldCannotBeNull(String message) {
        super(message);
    }

    public FieldCannotBeNull(String message, Throwable cause) {
        super(message, cause);
    }

    public FieldCannotBeNull(Throwable cause) {
        super(cause);
    }

    public FieldCannotBeNull(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
