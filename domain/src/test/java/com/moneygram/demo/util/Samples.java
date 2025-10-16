package com.moneygram.demo.util;

import com.moneygram.demo.kernel.command.CreateBookCommand;
import com.moneygram.demo.kernel.command.DeleteBookCommand;
import com.moneygram.demo.kernel.command.UpdateBookCommand;
import com.moneygram.demo.kernel.model.Book;
import com.moneygram.demo.kernel.query.GetBookByIdQuery;
import com.moneygram.demo.kernel.query.GetBooksQuery;

import static com.moneygram.demo.util.Constants.*;

public class Samples {
    public static CreateBookCommand createBookCommandSample(){
        return new CreateBookCommand(TITLE, AUTHOR, EDITION, YEAR);
    }

    public static Book bookSample(){
        return Book.builder()
                .bookId(BOOK_ID)
                .title(TITLE)
                .author(AUTHOR)
                .edition(EDITION)
                .year(YEAR)
                .build();
    }

    public static GetBooksQuery getBooksQuerySample(){
        return new GetBooksQuery(OFFSET, LIMIT);
    }

    public static GetBookByIdQuery getBookByIdQuerySample(){
        return new GetBookByIdQuery(BOOK_ID);
    }

    public static DeleteBookCommand deleteBookCommandSample(){
        return new DeleteBookCommand(BOOK_ID);
    }

    public static UpdateBookCommand updateBookCommandSample(){
        return new UpdateBookCommand(BOOK_ID, AUTHOR, TITLE, null, null);
    }
}
