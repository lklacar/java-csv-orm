package com.lukaklacar.csvorm.exceptions;

public class CannotCreateObjectInstanceException extends CsvOrmException {
    public CannotCreateObjectInstanceException() {
    }

    public CannotCreateObjectInstanceException(String message) {
        super(message);
    }

    public CannotCreateObjectInstanceException(String message, Throwable cause) {
        super(message, cause);
    }

    public CannotCreateObjectInstanceException(Throwable cause) {
        super(cause);
    }

    public CannotCreateObjectInstanceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
