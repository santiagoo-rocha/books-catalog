package com.moneygram.domain.usecase.book;

import com.moneygram.domain.kernel.command.CreateBookCommand;
import com.moneygram.domain.kernel.model.Book;
import com.moneygram.domain.port.repository.BookRepositoryPort;
import com.moneygram.domain.usecase.UseCase;
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
