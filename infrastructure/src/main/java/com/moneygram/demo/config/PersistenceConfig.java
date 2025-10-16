package com.moneygram.demo.config;

import com.moneygram.demo.port.repository.BookRepositoryPort;
import com.moneygram.demo.adapter.out.repository.BookRepositoryInMemoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ConcurrentSkipListMap;

@Configuration
public class PersistenceConfig {

    @Bean
    public BookRepositoryPort bookRepository() {
        return new BookRepositoryInMemoryAdapter(new ConcurrentSkipListMap<>());
    }

}
