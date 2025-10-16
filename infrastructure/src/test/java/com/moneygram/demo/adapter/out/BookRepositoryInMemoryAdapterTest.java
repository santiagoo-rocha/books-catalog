package com.moneygram.demo.adapter.out;

import com.moneygram.demo.adapter.out.repository.BookRepositoryInMemoryAdapter;
import com.moneygram.demo.adapter.out.repository.dto.BookEntity;
import com.moneygram.demo.adapter.out.repository.exception.BookRepositoryErrorStatus;
import com.moneygram.demo.adapter.out.repository.exception.BookRepositoryException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.test.StepVerifier;

import java.util.List;
import java.util.Map;

import static com.moneygram.demo.util.Constants.*;
import static com.moneygram.demo.util.Samples.*;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookRepositoryInMemoryAdapterTest {

    @Mock
    private Map<String, BookEntity> databaseMap;

    @InjectMocks
    private BookRepositoryInMemoryAdapter bookRepositoryInMemoryAdapter;

    @Test
    public void shouldGetBooks(){
        when(databaseMap.values()).thenReturn(List.of(bookEntitySample()));

        StepVerifier.create(bookRepositoryInMemoryAdapter.getBooks(getBooksRequestSample()))
                .assertNext(it -> {
                    Assertions.assertEquals(BOOK_ID,it.bookId());
                    Assertions.assertEquals(TITLE, it.title());
                    Assertions.assertEquals(AUTHOR, it.author());
                    Assertions.assertEquals(YEAR, it.year());
                    Assertions.assertEquals(EDITION, it.edition());
                })
                .verifyComplete();
    }

    @Test
    public void shouldFailGettingBooks(){
        when(databaseMap.values()).thenThrow(new RuntimeException("Unknown exception"));

        StepVerifier.create(bookRepositoryInMemoryAdapter.getBooks(getBooksRequestSample()))
                .expectErrorSatisfies(ex -> {
                    assertInstanceOf(BookRepositoryException.class, ex);
                    BookRepositoryException bookRepositoryException = (BookRepositoryException) ex;
                    Assertions.assertEquals(BookRepositoryErrorStatus.CTL_100.name(), bookRepositoryException.getCode());
                    Assertions.assertEquals(BookRepositoryErrorStatus.CTL_100.getMessage(), bookRepositoryException.getMessage());
                })
                .verify();
    }

    @Test
    public void shouldGetBookById(){
        when(databaseMap.getOrDefault(anyString(), any())).thenReturn(bookEntitySample());

        StepVerifier.create(bookRepositoryInMemoryAdapter.getBookById(BOOK_ID))
                .assertNext(it -> {
                    Assertions.assertEquals(BOOK_ID,it.bookId());
                    Assertions.assertEquals(TITLE, it.title());
                    Assertions.assertEquals(AUTHOR, it.author());
                    Assertions.assertEquals(YEAR, it.year());
                    Assertions.assertEquals(EDITION, it.edition());
                })
                .verifyComplete();
    }

    @Test
    public void shouldFailGettingBookNotFound(){
        when(databaseMap.getOrDefault(anyString(), any())).thenReturn(null);

        StepVerifier.create(bookRepositoryInMemoryAdapter.getBookById(BOOK_ID))
                .expectErrorSatisfies(ex -> {
                    assertInstanceOf(BookRepositoryException.class, ex);
                    BookRepositoryException bookRepositoryException = (BookRepositoryException) ex;
                    Assertions.assertEquals(BookRepositoryErrorStatus.CTL_101.name(), bookRepositoryException.getCode());
                    Assertions.assertEquals(BookRepositoryErrorStatus.CTL_101.getMessage(), bookRepositoryException.getMessage());
                })
                .verify();
    }

    @Test
    public void shouldSaveBook(){
        when(databaseMap.put(anyString(), any())).thenReturn(bookEntitySample());

        StepVerifier.create(bookRepositoryInMemoryAdapter.saveBook(bookSample()))
                .assertNext(it -> {
                    Assertions.assertEquals(BOOK_ID,it.bookId());
                    Assertions.assertEquals(TITLE, it.title());
                    Assertions.assertEquals(AUTHOR, it.author());
                    Assertions.assertEquals(YEAR, it.year());
                    Assertions.assertEquals(EDITION, it.edition());
                })
                .verifyComplete();
    }

    @Test
    public void shouldFailSavingBook(){
        when(databaseMap.put(anyString(), any())).thenThrow(new RuntimeException("Unknown exception"));

        StepVerifier.create(bookRepositoryInMemoryAdapter.saveBook(bookSample()))
                .expectErrorSatisfies(ex -> {
                    assertInstanceOf(BookRepositoryException.class, ex);
                    BookRepositoryException bookRepositoryException = (BookRepositoryException) ex;
                    Assertions.assertEquals(BookRepositoryErrorStatus.CTL_102.name(), bookRepositoryException.getCode());
                    Assertions.assertEquals(BookRepositoryErrorStatus.CTL_102.getMessage(), bookRepositoryException.getMessage());
                })
                .verify();
    }

    @Test
    public void shouldDeleteBook(){
        when(databaseMap.remove(anyString())).thenReturn(bookEntitySample());

        StepVerifier.create(bookRepositoryInMemoryAdapter.deleteBook(BOOK_ID))
                .verifyComplete();

        verify(databaseMap).remove(BOOK_ID);
    }

    @Test
    public void shouldFailDeletingBook(){
        when(databaseMap.remove(anyString())).thenThrow(new RuntimeException("Unknown exception"));

        StepVerifier.create(bookRepositoryInMemoryAdapter.deleteBook(BOOK_ID))
                .expectErrorSatisfies(ex -> {
                    assertInstanceOf(BookRepositoryException.class, ex);
                    BookRepositoryException bookRepositoryException = (BookRepositoryException) ex;
                    Assertions.assertEquals(BookRepositoryErrorStatus.CTL_103.name(), bookRepositoryException.getCode());
                    Assertions.assertEquals(BookRepositoryErrorStatus.CTL_103.getMessage(), bookRepositoryException.getMessage());
                })
                .verify();
    }
}
