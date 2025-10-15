package com.moneygram.infrastructure.adapter.out.repository.exception;

import com.moneygram.domain.kernel.exception.BusinessException;

public class BookRepositoryException extends BusinessException {

    public BookRepositoryException(BookRepositoryErrorStatus errorStatus) {
        super(errorStatus.name(), errorStatus.getMessage());
    }

    public static BookRepositoryException unknownError() {
        return new BookRepositoryException(BookRepositoryErrorStatus.CTL_100);
    }

    public static BookRepositoryException notFoundError() {
        return new BookRepositoryException(BookRepositoryErrorStatus.CTL_101);
    }
}
