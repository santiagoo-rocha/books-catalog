package com.moneygram.demo.adapter.in.api.book;

import com.moneygram.demo.adapter.in.api.book.v1.BookController;
import com.moneygram.demo.adapter.in.api.book.v1.dto.BookResponse;
import com.moneygram.demo.adapter.in.api.dto.ErrorResponse;
import com.moneygram.demo.adapter.out.repository.exception.BookRepositoryErrorStatus;
import com.moneygram.demo.adapter.out.repository.exception.BookRepositoryException;
import com.moneygram.demo.usecase.book.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.moneygram.demo.util.Constants.*;
import static com.moneygram.demo.util.Samples.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@WebFluxTest(controllers = BookController.class)
public class BookControllerTest {
    @MockitoBean
    private GetBooksUseCase getBooksUseCase;

    @MockitoBean
    private GetBookByIdUseCase getBookByIdUseCase;

    @MockitoBean
    private CreateBookUseCase createBookUseCase;

    @MockitoBean
    private UpdateBookUseCase updateBookUseCase;

    @MockitoBean
    private DeleteBookUseCase deleteBookUseCase;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void shouldGetAllBooks() {
        when(getBooksUseCase.execute(any())).thenReturn(Flux.just(bookSample()));

        webTestClient
                .get()
                .uri("/v1/books")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(BookResponse.class)
                .consumeWith(response -> {
                    List<BookResponse> booksResponse = response.getResponseBody();
                    Assertions.assertNotNull(booksResponse);
                    Assertions.assertEquals(1, booksResponse.size());
                    Assertions.assertEquals(BOOK_ID, booksResponse.getFirst().bookId());
                    Assertions.assertEquals(TITLE, booksResponse.getFirst().title());
                    Assertions.assertEquals(AUTHOR, booksResponse.getFirst().author());
                    Assertions.assertEquals(YEAR, booksResponse.getFirst().year());
                    Assertions.assertEquals(EDITION, booksResponse.getFirst().edition());
                });
    }

    @Test
    public void shouldGetBookById() {
        when(getBookByIdUseCase.execute(any())).thenReturn(Mono.just(bookSample()));

        webTestClient
                .get()
                .uri("/v1/books/{bookId}", BOOK_ID)
                .exchange()
                .expectStatus().isOk()
                .expectBody(BookResponse.class)
                .consumeWith(response -> {
                    BookResponse bookResponse = response.getResponseBody();
                    Assertions.assertNotNull(bookResponse);
                    Assertions.assertEquals(BOOK_ID, bookResponse.bookId());
                    Assertions.assertEquals(TITLE, bookResponse.title());
                    Assertions.assertEquals(AUTHOR, bookResponse.author());
                    Assertions.assertEquals(YEAR, bookResponse.year());
                    Assertions.assertEquals(EDITION, bookResponse.edition());
                });
    }

    @Test
    public void shouldFailGettingBookNotFound() {
        when(getBookByIdUseCase.execute(any())).thenReturn(Mono.error(BookRepositoryException.notFoundError()));

        webTestClient
                .get()
                .uri("/v1/books/{bookId}", BOOK_ID)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(ErrorResponse.class)
                .consumeWith(response -> {
                    ErrorResponse errorResponse = response.getResponseBody();
                    Assertions.assertNotNull(errorResponse);
                    Assertions.assertEquals(BookRepositoryErrorStatus.CTL_101.name(), errorResponse.code());
                    Assertions.assertEquals(BookRepositoryErrorStatus.CTL_101.getMessage(), errorResponse.detail());
                });
    }

    @Test
    public void shouldCreateBook() {
        when(createBookUseCase.execute(any())).thenReturn(Mono.just(bookSample()));

        webTestClient
                .post()
                .uri("/v1/books")
                .bodyValue(createBookRequestSample())
                .exchange()
                .expectStatus().isCreated()
                .expectBody(BookResponse.class)
                .consumeWith(response -> {
                    BookResponse bookResponse = response.getResponseBody();
                    Assertions.assertNotNull(bookResponse);
                    Assertions.assertEquals(BOOK_ID, bookResponse.bookId());
                    Assertions.assertEquals(TITLE, bookResponse.title());
                    Assertions.assertEquals(AUTHOR, bookResponse.author());
                    Assertions.assertEquals(YEAR, bookResponse.year());
                    Assertions.assertEquals(EDITION, bookResponse.edition());
                });
    }

    @Test
    public void shouldUpdateBook() {
        when(updateBookUseCase.execute(any())).thenReturn(Mono.just(bookSample()));

        webTestClient
                .patch()
                .uri("/v1/books/{bookId}", BOOK_ID)
                .bodyValue(createBookRequestSample())
                .exchange()
                .expectStatus().isOk()
                .expectBody(BookResponse.class);
    }

    @Test
    public void shouldDeleteBook() {
        when(deleteBookUseCase.execute(any())).thenReturn(Mono.empty());

        webTestClient
                .delete()
                .uri("/v1/books/{bookId}", BOOK_ID)
                .exchange()
                .expectStatus().isNoContent();
    }
}
