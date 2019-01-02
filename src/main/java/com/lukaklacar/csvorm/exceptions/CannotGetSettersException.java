package com.lukaklacar.csvorm.exceptions;

import java.beans.IntrospectionException;

public class CannotGetSettersException extends CsvOrmException {
    public CannotGetSettersException() {
    }

    public CannotGetSettersException(String message) {
        super(message);
    }

    public CannotGetSettersException(String message, Throwable cause) {
        super(message, cause);
    }

    public CannotGetSettersException(Throwable cause) {
        super(cause);
    }

    public CannotGetSettersException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
