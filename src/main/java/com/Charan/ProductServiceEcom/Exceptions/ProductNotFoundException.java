package com.Charan.ProductServiceEcom.Exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductNotFoundException extends Exception {

    private String resolution;

    public ProductNotFoundException(String message , String resolution) {
        super(message);
        this.resolution = resolution;
    }

}
