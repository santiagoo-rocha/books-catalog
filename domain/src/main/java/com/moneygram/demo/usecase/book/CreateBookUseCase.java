package com.moneygram.demo.usecase.book;

import com.moneygram.demo.kernel.command.CreateBookCommand;
import com.moneygram.demo.kernel.model.Book;
import com.moneygram.demo.port.repository.BookRepositoryPort;
import com.moneygram.demo.usecase.UseCase;
import reactor.core.publisher.Mono;

import java.util.UUID;

public class CreateBookUseCase implements UseCase <CreateBookCommand, Mono<Book>> {

    private final BookRepositoryPort bookRepositoryPort;

    public CreateBookUseCase(BookRepositoryPort bookRepositoryPort) {
        this.bookRepositoryPort = bookRepositoryPort;
    }

    @Override
    public Mono<Book> execute(CreateBookCommand command) {
        return Mono.just(buildBook(command))
                .flatMap(bookRepositoryPort::saveBook);
    }

    private Book buildBook(CreateBookCommand command) {
        return Book.builder()
                .bookId(UUID.randomUUID().toString())
                .title(command.title())
                .author(command.author())
                .year(command.year())
                .edition(command.edition())
                .build();
    }
}
