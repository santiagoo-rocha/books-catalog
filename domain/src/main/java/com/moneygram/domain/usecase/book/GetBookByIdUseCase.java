package com.moneygram.domain.usecase.book;

import com.moneygram.domain.kernel.model.Book;
import com.moneygram.domain.kernel.query.GetBookByIdQuery;
import com.moneygram.domain.port.repository.BookRepositoryPort;
import com.moneygram.domain.usecase.UseCase;
import reactor.core.publisher.Mono;

public class GetBookByIdUseCase implements UseCase<GetBookByIdQuery, Mono<Book>> {

    private final BookRepositoryPort bookRepositoryPort;

    public GetBookByIdUseCase(BookRepositoryPort bookRepositoryPort) {
        this.bookRepositoryPort = bookRepositoryPort;
    }

    @Override
    public Mono<Book> execute(GetBookByIdQuery command) {
        return bookRepositoryPort.getBookById(command.bookId());
    }
}
