package com.moneygram.demo.util;

import com.moneygram.demo.adapter.in.api.book.v1.dto.CreateBookRequest;
import com.moneygram.demo.adapter.out.repository.dto.BookEntity;
import com.moneygram.demo.kernel.model.Book;
import com.moneygram.demo.port.repository.dto.GetBooksRequest;

import static com.moneygram.demo.util.Constants.*;

public class Samples {

    public static BookEntity bookEntitySample() {
        return BookEntity.builder()
                .bookId(BOOK_ID)
                .author(AUTHOR)
                .title(TITLE)
                .year(YEAR)
                .edition(EDITION)
                .build();
    }

    public static Book bookSample() {
        return Book.builder()
                .bookId(BOOK_ID)
                .author(AUTHOR)
                .title(TITLE)
                .year(YEAR)
                .edition(EDITION)
                .build();
    }

    public static GetBooksRequest getBooksRequestSample() {
        return new GetBooksRequest(OFFSET, LIMIT);
    }

    public static CreateBookRequest createBookRequestSample() {
        return new CreateBookRequest(TITLE, AUTHOR, YEAR, EDITION);
    }
}
