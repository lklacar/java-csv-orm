package com.lukaklacar.csvorm.exceptions;

public class CannotGetGettersExceptioon extends CSVORMException {
    public CannotGetGettersExceptioon() {
    }

    public CannotGetGettersExceptioon(String message) {
        super(message);
    }

    public CannotGetGettersExceptioon(String message, Throwable cause) {
        super(message, cause);
    }

    public CannotGetGettersExceptioon(Throwable cause) {
        super(cause);
    }

    public CannotGetGettersExceptioon(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
