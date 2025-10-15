package com.moneygram.domain.kernel.command;

public record CreateBookCommand(
        String title,
        String author,
        int edition,
        int year
) {}
