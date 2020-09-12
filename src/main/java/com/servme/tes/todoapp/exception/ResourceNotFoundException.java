package com.servme.tes.todoapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.function.Supplier;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException implements Supplier<ResourceNotFoundException> {
    public ResourceNotFoundException(String message) {
        super(message);
    }

    @Override
    public ResourceNotFoundException get() {
        return new ResourceNotFoundException(getMessage());
    }
}
