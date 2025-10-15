package com.moneygram.domain.kernel.exception;

public class BusinessException extends RuntimeException {
    private final String code;
    private final String message;

    public BusinessException(final String code, final String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return String.format("Business code %s, message %s", code, message);
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
