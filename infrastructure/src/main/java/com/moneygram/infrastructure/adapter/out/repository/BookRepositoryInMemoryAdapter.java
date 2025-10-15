package com.moneygram.infrastructure.adapter.out.repository;

import com.moneygram.domain.kernel.model.Book;
import com.moneygram.domain.port.repository.BookRepositoryPort;
import com.moneygram.domain.port.repository.dto.GetBooksRequest;
import com.moneygram.infrastructure.adapter.out.repository.dto.BookEntity;
import com.moneygram.infrastructure.adapter.out.repository.exception.BookRepositoryException;
import com.moneygram.infrastructure.adapter.out.repository.mapper.BookRepositoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.ConcurrentSkipListMap;

public class BookRepositoryInMemoryAdapter implements BookRepositoryPort {

    private static final ConcurrentSkipListMap<String, BookEntity> database = new ConcurrentSkipListMap<>();

    private final Logger logger = LoggerFactory.getLogger(BookRepositoryInMemoryAdapter.class);

    @Override
    public Mono<Book> getBookById(String id) {
        return Mono.justOrEmpty(database.getOrDefault(id, null))
                .doOnError(it -> logger.error("Error getting book with id {}, err {}", id, it.getMessage()))
                .onErrorMap(_ -> BookRepositoryException.unknownError())
                .switchIfEmpty(Mono.error(BookRepositoryException.notFoundError()))
                .map(BookRepositoryMapper::toBook);
    }

    @Override
    public Flux<Book> getBooks(GetBooksRequest getBooksRequest) {
        return Flux.fromStream(database.values()
                .stream()
                .skip(getBooksRequest.offset())
                .limit(getBooksRequest.limit()))
                .map(BookRepositoryMapper::toBook)
                .doOnError(it -> logger.error("Error getting books err {}", it.getMessage()))
                .onErrorMap(_ -> BookRepositoryException.unknownError());
    }

    @Override
    public Mono<Book> saveBook(final Book book) {
        return Mono.just(book)
                .map(BookRepositoryMapper::toBookEntity)
                .mapNotNull(it -> database.put(book.bookId(), it))
                .thenReturn(book)
                .doOnError(it -> logger.error("Error saving book with id {}, err {}", book.bookId(), it.getMessage()))
                .onErrorMap(_ -> BookRepositoryException.unknownError());
    }

    @Override
    public Mono<Void> deleteBook(String id) {
        return Mono.just(database.remove(id))
                .doOnError(it -> logger.error("Error deleting book with id {}, err {}", id, it.getMessage()))
                .onErrorMap(_ -> BookRepositoryException.unknownError())
                .then();
    }
}
