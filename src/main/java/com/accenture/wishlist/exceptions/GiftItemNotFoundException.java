package com.accenture.wishlist.exceptions;

public class GiftItemNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 2;
    public GiftItemNotFoundException(String message) {
        super(message);
    }
}
