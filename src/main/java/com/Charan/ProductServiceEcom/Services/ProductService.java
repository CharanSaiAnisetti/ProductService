package com.Charan.ProductServiceEcom.Services;

import com.Charan.ProductServiceEcom.Models.Product;

import java.util.List;

public interface ProductService {

    public Product getSingleProduct(Long id);

    public List<Product> getAllProducts();
}
