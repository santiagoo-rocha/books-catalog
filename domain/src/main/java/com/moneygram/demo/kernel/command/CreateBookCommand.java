package com.moneygram.demo.kernel.command;

public record CreateBookCommand(
        String title,
        String author,
        int edition,
        int year
) {}
