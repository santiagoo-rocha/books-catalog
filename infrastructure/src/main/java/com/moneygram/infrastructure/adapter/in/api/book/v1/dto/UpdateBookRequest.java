package com.moneygram.infrastructure.adapter.in.api.book.v1.dto;

public record UpdateBookRequest(
        String title,
        String author,
        Integer year,
        Integer edition
) {}
