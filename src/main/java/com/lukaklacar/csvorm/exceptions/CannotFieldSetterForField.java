package com.lukaklacar.csvorm.exceptions;

public class CannotFieldSetterForField extends CSVORMException {

    public CannotFieldSetterForField() {
    }

    public CannotFieldSetterForField(String message) {
        super(message);
    }

    public CannotFieldSetterForField(String message, Throwable cause) {
        super(message, cause);
    }

    public CannotFieldSetterForField(Throwable cause) {
        super(cause);
    }

    public CannotFieldSetterForField(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
