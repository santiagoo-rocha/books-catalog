package com.moneygram.demo.config;

import com.moneygram.demo.port.repository.BookRepositoryPort;
import com.moneygram.demo.usecase.book.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookConfiguration {

    @Bean
    public GetBooksUseCase getBooksUseCase(BookRepositoryPort bookRepositoryPort) {
        return new GetBooksUseCase(bookRepositoryPort);
    }

    @Bean
    public GetBookByIdUseCase getBookByIdUseCase(BookRepositoryPort bookRepositoryPort) {
        return new GetBookByIdUseCase(bookRepositoryPort);
    }

    @Bean
    public CreateBookUseCase createBookUseCase(BookRepositoryPort bookRepositoryPort) {
        return new CreateBookUseCase(bookRepositoryPort);
    }

    @Bean
    public UpdateBookUseCase updateBookUseCase(BookRepositoryPort bookRepositoryPort) {
        return new UpdateBookUseCase(bookRepositoryPort);
    }

    @Bean
    public DeleteBookUseCase deleteBookUseCase(BookRepositoryPort bookRepositoryPort) {
        return new DeleteBookUseCase(bookRepositoryPort);
    }
}
