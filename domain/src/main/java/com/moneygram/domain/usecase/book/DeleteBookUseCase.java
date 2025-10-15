package com.moneygram.domain.usecase.book;

import com.moneygram.domain.kernel.command.DeleteBookCommand;
import com.moneygram.domain.port.repository.BookRepositoryPort;
import com.moneygram.domain.usecase.UseCase;
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
