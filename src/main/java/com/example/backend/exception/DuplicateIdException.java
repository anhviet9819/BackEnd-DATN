package com.example.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DuplicateIdException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String resourceName;
    private Long idValue;

    public DuplicateIdException( String resourceName, Long idValue) {
        super(String.format("%s id '%s' already exists ", resourceName , idValue ));
        this.resourceName = resourceName;
        this.idValue = idValue;
    }

    public String getResourceName() {
        return resourceName;
    }
    public Object getIdValue() {
        return idValue;
    }
}