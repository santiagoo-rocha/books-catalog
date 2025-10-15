package com.moneygram.infrastructure.adapter.out.repository.exception;

public enum BookRepositoryErrorStatus {
    CTL_100("Unknown error"),
    CTL_101("Book not found");

    private final String message;

    BookRepositoryErrorStatus(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
