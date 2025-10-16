package com.moneygram.demo.adapter.out.repository;

import com.moneygram.demo.kernel.model.Book;
import com.moneygram.demo.port.repository.BookRepositoryPort;
import com.moneygram.demo.port.repository.dto.GetBooksRequest;
import com.moneygram.demo.adapter.out.repository.dto.BookEntity;
import com.moneygram.demo.adapter.out.repository.exception.BookRepositoryException;
import com.moneygram.demo.adapter.out.repository.mapper.BookRepositoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

public class BookRepositoryInMemoryAdapter implements BookRepositoryPort {

    private final Map<String, BookEntity> database;

    private final Logger logger = LoggerFactory.getLogger(BookRepositoryInMemoryAdapter.class);

    public BookRepositoryInMemoryAdapter(Map<String, BookEntity> database) {
        this.database = database;
    }

    @Override
    public Mono<Book> getBookById(String id) {
        return Mono.justOrEmpty(database.getOrDefault(id, null))
                .doOnError(it -> logger.error("Error getting book with id {}, err {}", id, it.getMessage()))
                .onErrorMap(_ -> BookRepositoryException.errorGettingBookById())
                .switchIfEmpty(Mono.error(BookRepositoryException.notFoundError()))
                .map(BookRepositoryMapper::toBook);
    }

    @Override
    public Flux<Book> getBooks(GetBooksRequest getBooksRequest) {
        return Mono.fromCallable(() -> database.values()
                .stream()
                .skip(getBooksRequest.offset())
                .limit(getBooksRequest.limit()))
                .flatMapMany(Flux::fromStream)
                .map(BookRepositoryMapper::toBook)
                .doOnError(it -> logger.error("Error getting books err {}", it.getMessage()))
                .onErrorMap(_ -> BookRepositoryException.errorGettingBooks());
    }

    @Override
    public Mono<Book> saveBook(final Book book) {
        return Mono.just(book)
                .map(BookRepositoryMapper::toBookEntity)
                .mapNotNull(it -> database.put(book.bookId(), it))
                .thenReturn(book)
                .doOnError(it -> logger.error("Error saving book with id {}, err {}", book.bookId(), it.getMessage()))
                .onErrorMap(_ -> BookRepositoryException.errorSavingBook());
    }

    @Override
    public Mono<Void> deleteBook(String id) {
        return Mono.fromCallable(() -> database.remove(id))
                .doOnError(it -> logger.error("Error deleting book with id {}, err {}", id, it.getMessage()))
                .onErrorMap(_ -> BookRepositoryException.errorDeletingBook())
                .then();
    }
}
