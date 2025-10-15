package com.moneygram.domain.usecase.book;

import com.moneygram.domain.kernel.model.Book;
import com.moneygram.domain.kernel.query.GetBooksQuery;
import com.moneygram.domain.port.repository.BookRepositoryPort;
import com.moneygram.domain.port.repository.dto.GetBooksRequest;
import com.moneygram.domain.usecase.UseCase;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class GetBooksUseCase implements UseCase<GetBooksQuery, Flux<Book>> {

    private final BookRepositoryPort bookRepositoryPort;

    public GetBooksUseCase(BookRepositoryPort bookRepositoryPort) {
        this.bookRepositoryPort = bookRepositoryPort;
    }

    @Override
    public Flux<Book> execute(GetBooksQuery command) {
        return Mono.just(buildRequest(command))
                .flatMapMany(bookRepositoryPort::getBooks);
    }

    private GetBooksRequest buildRequest(GetBooksQuery query){
        return new GetBooksRequest(query.offset(), query.limit());
    }
}
