package com.moneygram.demo.adapter.in.api.dto;

public record ErrorResponse(
        String code,
        String detail
) {
}
