package com.supun.productapi.errorhandler;

public class ProductAlreadyExist extends  RuntimeException{
    public ProductAlreadyExist(String message) {
        super(message);
    }

}
