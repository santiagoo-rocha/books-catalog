package com.moneygram.demo.adapter.in.api.book.v1.dto;

public record UpdateBookRequest(
        String title,
        String author,
        Integer year,
        Integer edition
) {}
