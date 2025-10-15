package com.moneygram.infrastructure.adapter.in.api.book.v1.dto;

public record BookResponse(
        String bookId,
        String title,
        String author,
        int year,
        int edition
) {}
