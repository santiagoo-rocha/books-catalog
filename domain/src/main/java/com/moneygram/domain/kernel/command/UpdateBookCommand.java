package com.moneygram.domain.kernel.command;

public record UpdateBookCommand(
        String bookId,
        String title,
        String author,
        Integer year,
        Integer edition
) {}

