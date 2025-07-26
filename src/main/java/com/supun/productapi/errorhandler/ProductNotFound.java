package com.supun.productapi.errorhandler;

public class ProductNotFound extends  RuntimeException {
    public ProductNotFound(String message) {
        super(message);
    }
}
