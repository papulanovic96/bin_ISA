package com.bin448.backend.exception;

public class ForeignKeyConstraintException extends RuntimeException {

    public ForeignKeyConstraintException(String reason) {
        super(reason);
    }

    public ForeignKeyConstraintException(String reason, Throwable cause) {
        super(reason, cause);
    }

}
