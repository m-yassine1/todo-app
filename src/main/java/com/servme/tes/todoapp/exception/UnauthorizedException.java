package com.servme.tes.todoapp.exception;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.function.Supplier;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends RuntimeException implements Supplier<UnauthorizedException> {

    public UnauthorizedException(String message) {
        super(message);
    }

    @Override
    public UnauthorizedException get() {
        return new UnauthorizedException(getMessage());
    }
}
