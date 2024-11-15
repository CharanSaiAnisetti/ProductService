package com.Charan.ProductServiceEcom.Services;

import com.Charan.ProductServiceEcom.Exceptions.ProductNotFoundException;
import com.Charan.ProductServiceEcom.Models__FakeStoreProductService.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {

    public ResponseEntity<Product> getSingleProduct(Long id) throws ProductNotFoundException;

    public List<Product> getAllProducts();

    public Product deleteProduct(Long id) throws ProductNotFoundException;

    public Product updateProduct(Long productId, Product product);

    Product replaceProduct(Long productId, Product product);


    Product createProduct(String title, Double price, String description, String image, String category);

    List<String> getAllCategories();

    List<Product> getProductsByCategory(String category);
}
