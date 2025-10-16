package com.moneygram.demo.adapter.out.repository.exception;

public enum BookRepositoryErrorStatus {
    CTL_100("Error getting books"),
    CTL_101("Book not found"),
    CTL_102("Error saving book"),
    CTL_103("Error deleting book"),
    CTL_104("Error getting book");

    private final String message;

    BookRepositoryErrorStatus(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
