package com.accenture.wishlist.exceptions;

public class WishlistNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1;

    public WishlistNotFoundException(String message) {
        super(message);
    }

}
