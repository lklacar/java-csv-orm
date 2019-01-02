package com.lukaklacar.csvorm.exceptions;

import java.beans.IntrospectionException;

public class CannotGetPropertyDescriptors extends CsvOrmException {
    public CannotGetPropertyDescriptors() {
    }

    public CannotGetPropertyDescriptors(String message) {
        super(message);
    }

    public CannotGetPropertyDescriptors(String message, Throwable cause) {
        super(message, cause);
    }

    public CannotGetPropertyDescriptors(Throwable cause) {
        super(cause);
    }

    public CannotGetPropertyDescriptors(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
