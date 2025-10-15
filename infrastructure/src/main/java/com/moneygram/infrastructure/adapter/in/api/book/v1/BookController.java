package com.moneygram.infrastructure.adapter.in.api.book.v1;

import com.moneygram.domain.kernel.command.DeleteBookCommand;
import com.moneygram.domain.kernel.query.GetBookByIdQuery;
import com.moneygram.domain.kernel.query.GetBooksQuery;
import com.moneygram.domain.usecase.book.*;
import com.moneygram.infrastructure.adapter.in.api.book.v1.dto.BookResponse;
import com.moneygram.infrastructure.adapter.in.api.book.v1.dto.CreateBookRequest;
import com.moneygram.infrastructure.adapter.in.api.book.v1.dto.UpdateBookRequest;
import com.moneygram.infrastructure.adapter.in.api.book.v1.mapper.BookMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/books")
public class BookController {

    private final CreateBookUseCase createBookUseCase;
    private final UpdateBookUseCase updateBookUseCase;
    private final GetBooksUseCase getBooksUseCase;
    private final GetBookByIdUseCase getBookByIdUseCase;
    private final DeleteBookUseCase deleteBookUseCase;


    public BookController(
            CreateBookUseCase createBookUseCase,
            UpdateBookUseCase updateBookUseCase,
            GetBooksUseCase getBooksUseCase,
            GetBookByIdUseCase getBookByIdUseCase,
            DeleteBookUseCase deleteBookUseCase
    ) {
        this.createBookUseCase = createBookUseCase;
        this.updateBookUseCase = updateBookUseCase;
        this.getBooksUseCase = getBooksUseCase;
        this.getBookByIdUseCase = getBookByIdUseCase;
        this.deleteBookUseCase = deleteBookUseCase;
    }

    @GetMapping
    public Flux<BookResponse> getBooks(
            @RequestParam(value = "offset", defaultValue = "0", required = false) int offset,
            @RequestParam(value = "limit", defaultValue = "10", required = false) int limit
    ){
        return getBooksUseCase.execute(new GetBooksQuery(offset, limit))
                .map(BookMapper::toResponse);
    }

    @GetMapping("/{bookId}")
    public Mono<ResponseEntity<BookResponse>> getBookById(@PathVariable String bookId){
        return getBookByIdUseCase.execute(new GetBookByIdQuery(bookId))
                .map(BookMapper::toResponse)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    public Mono<ResponseEntity<BookResponse>> createBook(
            @RequestBody @Valid CreateBookRequest createBookRequest
    ){
        return Mono.just(BookMapper.toCreateBookCommand(createBookRequest))
                .flatMap(createBookUseCase::execute)
                .map(BookMapper::toResponse)
                .map(it -> new ResponseEntity<>(it, HttpStatus.CREATED));
    }

    @PatchMapping("/{bookId}")
    public Mono<ResponseEntity<BookResponse>> updateBook(
            @PathVariable String bookId,
            @RequestBody UpdateBookRequest updateBookRequest
    ){
        return Mono.just(BookMapper.toUpdateBookCommand(bookId, updateBookRequest))
                .flatMap(updateBookUseCase::execute)
                .map(BookMapper::toResponse)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{bookId}")
    public Mono<ResponseEntity<BookResponse>> deleteBookById(@PathVariable String bookId){
        return deleteBookUseCase.execute(new DeleteBookCommand(bookId))
                .thenReturn(ResponseEntity.noContent().build());
    }
}
