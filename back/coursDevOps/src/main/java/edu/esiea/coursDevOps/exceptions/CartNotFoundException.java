package edu.esiea.coursDevOps.exceptions;

public class CartNotFoundException extends Exception {
    public CartNotFoundException(String message) {
        super(message);
    }
}