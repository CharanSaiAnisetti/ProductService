package com.Charan.ProductServiceEcom.Exceptions;

public class CategoryNotFoundException extends Exception {
    String resolution;
    public CategoryNotFoundException(String message,String resolution) {
        super(message);
        this.resolution = resolution;
    }
}
