package com.moneygram.demo.usecase.book;

import com.moneygram.demo.kernel.model.Book;
import com.moneygram.demo.kernel.query.GetBookByIdQuery;
import com.moneygram.demo.port.repository.BookRepositoryPort;
import com.moneygram.demo.usecase.UseCase;
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
