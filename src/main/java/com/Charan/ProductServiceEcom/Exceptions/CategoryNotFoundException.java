package com.Charan.ProductServiceEcom.Exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryNotFoundException extends Exception {
   private String resolution;
    public CategoryNotFoundException(String message,String resolution) {
        super(message);
        this.resolution = resolution;
    }
}
