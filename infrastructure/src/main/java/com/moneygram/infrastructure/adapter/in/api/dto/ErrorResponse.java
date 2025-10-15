package com.moneygram.infrastructure.adapter.in.api.dto;

public record ErrorResponse(
        String code,
        String detail
) {
}
