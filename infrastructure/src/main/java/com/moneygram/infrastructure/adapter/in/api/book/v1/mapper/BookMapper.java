package com.moneygram.infrastructure.adapter.in.api.book.v1.mapper;

import com.moneygram.domain.kernel.command.CreateBookCommand;
import com.moneygram.domain.kernel.command.UpdateBookCommand;
import com.moneygram.domain.kernel.model.Book;
import com.moneygram.infrastructure.adapter.in.api.book.v1.dto.BookResponse;
import com.moneygram.infrastructure.adapter.in.api.book.v1.dto.CreateBookRequest;
import com.moneygram.infrastructure.adapter.in.api.book.v1.dto.UpdateBookRequest;

public class BookMapper {
    public static BookResponse toResponse(final Book book) {
        return new BookResponse(book.bookId(), book.title(), book.author(), book.year(),
                book.edition());
    }

    public static CreateBookCommand toCreateBookCommand(final CreateBookRequest request) {
        return new CreateBookCommand(request.title(), request.author(), request.year(),
                request.edition());
    }

    public static UpdateBookCommand toUpdateBookCommand(String bookId, final UpdateBookRequest request) {
        return new UpdateBookCommand(bookId, request.title(), request.author(), request.year(),
                request.edition());
    }
}
