package com.moneygram.demo.port.repository;

import com.moneygram.demo.kernel.model.Book;
import com.moneygram.demo.port.repository.dto.GetBooksRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookRepositoryPort {
    Mono<Book> getBookById(String id);
    Flux<Book> getBooks(GetBooksRequest getBooksRequest);
    Mono<Book> saveBook(Book book);
    Mono<Void> deleteBook(String id);
}
