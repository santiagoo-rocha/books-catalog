package com.moneygram.demo.adapter.in.api.book.v1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateBookRequest(
        @NotBlank String title,
        @NotBlank String author,
        @NotNull Integer year,
        @NotNull Integer edition
) {
}
