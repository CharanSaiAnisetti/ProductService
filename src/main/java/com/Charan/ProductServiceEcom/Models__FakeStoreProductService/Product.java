package com.Charan.ProductServiceEcom.Models__FakeStoreProductService;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends BaseModel {


    private String title;
    private Double price;
    private Category category;
}
