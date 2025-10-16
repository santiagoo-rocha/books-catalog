package com.moneygram.demo.usecase;

import reactor.core.publisher.Mono;

@FunctionalInterface
public interface UseCase<T, R> {
    R execute(T command);
}

