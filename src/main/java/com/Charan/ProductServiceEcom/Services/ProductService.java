package com.Charan.ProductServiceEcom.Services;

import com.Charan.ProductServiceEcom.Exceptions.CategoryNotFoundException;
import com.Charan.ProductServiceEcom.Exceptions.ProductNotFoundException;
import com.Charan.ProductServiceEcom.Models.Product;
import com.Charan.ProductServiceEcom.dtos.SendEmailEventDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {

    public ResponseEntity<Product> getSingleProduct(Long id) throws ProductNotFoundException;

    public List<Product> getAllProducts();

    public Product deleteProduct(Long id) throws ProductNotFoundException;

    public Product updateProduct(Long productId, Product product) throws ProductNotFoundException;

    Product replaceProduct(Long productId, Product product) throws ProductNotFoundException, CategoryNotFoundException;


    Product createProduct(String title, double price, String description, String category);

    List<String> getAllCategories();

    List<Product> getProductsByCategory(String category);

    Page<Product> getAllProducts(int pageNumber, int pageSize,String sortBy) throws ProductNotFoundException;

    Product emailDeletedProduct(Long id, SendEmailEventDto emailEventDto) throws ProductNotFoundException;
}
