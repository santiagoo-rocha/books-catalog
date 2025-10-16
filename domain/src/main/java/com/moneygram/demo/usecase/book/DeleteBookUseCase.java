package com.moneygram.demo.usecase.book;

import com.moneygram.demo.kernel.command.DeleteBookCommand;
import com.moneygram.demo.port.repository.BookRepositoryPort;
import com.moneygram.demo.usecase.UseCase;
import reactor.core.publisher.Mono;

public class DeleteBookUseCase implements UseCase<DeleteBookCommand, Mono<Void>> {

    private final BookRepositoryPort bookRepositoryPort;

    public DeleteBookUseCase(BookRepositoryPort bookRepositoryPort) {
        this.bookRepositoryPort = bookRepositoryPort;
    }

    @Override
    public Mono<Void> execute(DeleteBookCommand command) {
        return bookRepositoryPort.deleteBook(command.bookId());
    }
}
