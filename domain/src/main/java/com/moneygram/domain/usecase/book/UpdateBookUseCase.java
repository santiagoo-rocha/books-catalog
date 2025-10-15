package com.moneygram.domain.usecase.book;

import com.moneygram.domain.kernel.command.UpdateBookCommand;
import com.moneygram.domain.kernel.model.Book;
import com.moneygram.domain.port.repository.BookRepositoryPort;
import com.moneygram.domain.usecase.UseCase;
import reactor.core.publisher.Mono;

import java.util.Optional;

public class UpdateBookUseCase implements UseCase<UpdateBookCommand, Mono<Book>> {

    private final BookRepositoryPort bookRepositoryPort;

    public UpdateBookUseCase(BookRepositoryPort bookRepositoryPort) {
        this.bookRepositoryPort = bookRepositoryPort;
    }

    @Override
    public Mono<Book> execute(UpdateBookCommand command) {
        return bookRepositoryPort.getBookById(command.bookId())
                .map(it -> updateBook(command, it))
                .flatMap(bookRepositoryPort::saveBook);
    }

    private Book updateBook(UpdateBookCommand command, Book book) {
        return book.toBuilder()
                .title(Optional.ofNullable(command.title()).orElse(book.title()))
                .author(Optional.ofNullable(command.author()).orElse(book.author()))
                .year(Optional.ofNullable(command.year()).orElse(book.year()))
                .edition(Optional.ofNullable(command.edition()).orElse(book.edition()))
                .build();
    }
}
