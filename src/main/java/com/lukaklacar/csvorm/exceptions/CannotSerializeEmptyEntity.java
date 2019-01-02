package com.lukaklacar.csvorm.exceptions;

public class CannotSerializeEmptyEntity extends CsvOrmException {
    public CannotSerializeEmptyEntity() {
    }

    public CannotSerializeEmptyEntity(String message) {
        super(message);
    }

    public CannotSerializeEmptyEntity(String message, Throwable cause) {
        super(message, cause);
    }

    public CannotSerializeEmptyEntity(Throwable cause) {
        super(cause);
    }

    public CannotSerializeEmptyEntity(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
