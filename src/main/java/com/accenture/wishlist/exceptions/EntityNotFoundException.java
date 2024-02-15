package com.accenture.wishlist.exceptions;

public class EntityNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 3;
    public EntityNotFoundException(String message) {
        super(message);
    }
}
