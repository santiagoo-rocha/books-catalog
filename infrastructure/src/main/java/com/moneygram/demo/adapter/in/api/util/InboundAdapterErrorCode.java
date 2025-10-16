package com.moneygram.demo.adapter.in.api.util;

import com.moneygram.demo.adapter.out.repository.exception.BookRepositoryErrorStatus;
import org.springframework.http.HttpStatus;

import java.util.List;

public enum InboundAdapterErrorCode {
    NOT_FOUND(List.of(BookRepositoryErrorStatus.CTL_101.name()), HttpStatus.NOT_FOUND);

    private List<String> codes;
    private HttpStatus httpStatus;

    InboundAdapterErrorCode(final List<String> codes, final HttpStatus httpStatus) {
        this.codes = codes;
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public List<String> getCodes() {
        return codes;
    }
}
