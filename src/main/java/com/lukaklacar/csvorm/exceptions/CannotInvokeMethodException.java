package com.lukaklacar.csvorm.exceptions;

public class CannotInvokeMethodException extends CSVORMException {
    public CannotInvokeMethodException() {
    }

    public CannotInvokeMethodException(String message) {
        super(message);
    }

    public CannotInvokeMethodException(String message, Throwable cause) {
        super(message, cause);
    }

    public CannotInvokeMethodException(Throwable cause) {
        super(cause);
    }

    public CannotInvokeMethodException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
