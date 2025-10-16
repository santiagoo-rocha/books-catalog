package com.moneygram.demo.adapter.out.repository.exception;

import com.moneygram.demo.kernel.exception.BusinessException;

public class BookRepositoryException extends BusinessException {

    public BookRepositoryException(BookRepositoryErrorStatus errorStatus) {
        super(errorStatus.name(), errorStatus.getMessage());
    }

    public static BookRepositoryException errorGettingBooks() {
        return new BookRepositoryException(BookRepositoryErrorStatus.CTL_100);
    }

    public static BookRepositoryException notFoundError() {
        return new BookRepositoryException(BookRepositoryErrorStatus.CTL_101);
    }

    public static BookRepositoryException errorSavingBook() {
        return new BookRepositoryException(BookRepositoryErrorStatus.CTL_102);
    }

    public static BookRepositoryException errorDeletingBook() {
        return new BookRepositoryException(BookRepositoryErrorStatus.CTL_103);
    }

    public static BookRepositoryException errorGettingBookById() {
        return new BookRepositoryException(BookRepositoryErrorStatus.CTL_104);
    }
}
