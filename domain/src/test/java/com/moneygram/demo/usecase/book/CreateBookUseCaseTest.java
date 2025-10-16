package com.moneygram.demo.usecase.book;

import com.moneygram.demo.port.repository.BookRepositoryPort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static com.moneygram.demo.util.Constants.*;
import static com.moneygram.demo.util.Samples.createBookCommandSample;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateBookUseCaseTest {

    @Mock
    private BookRepositoryPort bookRepositoryPort;

    @InjectMocks
    private CreateBookUseCase createBookUseCase;

    @Test
    public void shouldCreateBook(){
        when(bookRepositoryPort.saveBook(any()))
                .thenAnswer(it -> Mono.just(it.getArguments()[0]));

        StepVerifier.create(createBookUseCase.execute(createBookCommandSample()))
                .assertNext(it -> {
                    Assertions.assertNotNull(it.bookId());
                    Assertions.assertEquals(TITLE, it.title());
                    Assertions.assertEquals(AUTHOR, it.author());
                    Assertions.assertEquals(YEAR, it.year());
                    Assertions.assertEquals(EDITION, it.edition());
                })
                .verifyComplete();
    }
}
