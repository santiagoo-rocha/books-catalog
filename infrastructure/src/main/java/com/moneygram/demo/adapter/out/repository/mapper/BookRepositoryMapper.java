package com.moneygram.demo.adapter.out.repository.mapper;

import com.moneygram.demo.kernel.model.Book;
import com.moneygram.demo.adapter.out.repository.dto.BookEntity;

public class BookRepositoryMapper {
    public static Book toBook(BookEntity entity) {
        return Book.builder()
                .bookId(entity.bookId())
                .title(entity.title())
                .author(entity.author())
                .year(entity.year())
                .edition(entity.edition())
                .build();
    }

    public static BookEntity toBookEntity(Book book) {
        return BookEntity.builder()
                .bookId(book.bookId())
                .title(book.title())
                .author(book.author())
                .year(book.year())
                .edition(book.edition())
                .build();
    }
}
