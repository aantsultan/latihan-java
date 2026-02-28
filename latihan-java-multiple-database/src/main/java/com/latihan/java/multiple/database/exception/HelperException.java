package com.latihan.java.multiple.database.exception;

import java.io.Serial;

public class HelperException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 7968913042433302035L;

    public HelperException(String message) {
        super(message);
    }

    public HelperException(String message, Throwable cause) {
        super(message, cause);
    }
}
