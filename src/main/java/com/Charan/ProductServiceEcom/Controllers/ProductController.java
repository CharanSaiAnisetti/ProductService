package com.Charan.ProductServiceEcom.Controllers;

import com.Charan.ProductServiceEcom.Models.Category;
import com.Charan.ProductServiceEcom.Models.Product;
import com.Charan.ProductServiceEcom.Services.ProductService;
import com.Charan.ProductServiceEcom.dtos.CreateProductRequestDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id) {
        return productService.getSingleProduct(id);
    }

    @GetMapping()
    public List<Product> GetAllProducts(){
        return productService.getAllProducts();
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(Long id){

    }

    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long productId, @RequestBody Product product){

        return productService.updateProduct(productId, product);
    }

    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long productId ,@RequestBody Product product){
        return productService.replaceProduct(productId, product);
    }

    @PostMapping()
    public Product createProduct(@RequestBody CreateProductRequestDto requestDto){
        return productService.createProduct(requestDto.getTitle(),requestDto.getPrice(),requestDto.getDescription(),requestDto.getImage(),requestDto.getCategory());
    }

    @GetMapping("/categories")
    public List<String> getAllCategories(){
        return productService.getAllCategories();
    }

}

