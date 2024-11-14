package com.Charan.ProductServiceEcom.Services;

import com.Charan.ProductServiceEcom.Models.Category;
import com.Charan.ProductServiceEcom.Models.Product;
import com.Charan.ProductServiceEcom.dtos.CreateProductRequestDto;

import java.util.List;

public interface ProductService {

    public Product getSingleProduct(Long id);

    public List<Product> getAllProducts();

    public void deleteProduct(Long id);

    public Product updateProduct(Long productId, Product product);

    Product replaceProduct(Long productId, Product product);


    Product createProduct(String title, Double price, String description, String image, String category);

    List<String> getAllCategories();
}
