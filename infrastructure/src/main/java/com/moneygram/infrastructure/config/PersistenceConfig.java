package com.moneygram.infrastructure.config;

import com.moneygram.domain.port.repository.BookRepositoryPort;
import com.moneygram.infrastructure.adapter.out.repository.BookRepositoryInMemoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersistenceConfig {

    @Bean
    public BookRepositoryPort bookRepository() {
        return new BookRepositoryInMemoryAdapter();
    }

}
