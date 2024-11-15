package com.Charan.ProductServiceEcom.Controllers;

import com.Charan.ProductServiceEcom.Exceptions.ProductNotFoundException;
import com.Charan.ProductServiceEcom.Models.Product;
import com.Charan.ProductServiceEcom.Services.ProductService;
import com.Charan.ProductServiceEcom.dtos.CreateProductRequestDto;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) throws ProductNotFoundException {
        return productService.getSingleProduct(id);
    }

    @GetMapping()
    public List<Product> GetAllProducts(){
        return productService.getAllProducts();
    }

    @DeleteMapping("/{id}")
    public Product deleteProduct(@PathVariable("id") Long id) throws ProductNotFoundException {
       return productService.deleteProduct(id);
    }

    //update API of FakeStore is not working
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

    @GetMapping("/categories/{category}")
    public List<Product> getProductsByCategory(@PathVariable("category") String category){
        return productService.getProductsByCategory(category);
    }

//    @ExceptionHandler(ProductNotFoundException.class)
//    public ResponseEntity<ExceptionDto> handleProductNotFoundException () {
//
//        ExceptionDto exceptionDto = new ExceptionDto();
//        exceptionDto.setErrorMessage("Product with the given id is not found ");
//        exceptionDto.setResolution("Please try a different id");
//        ResponseEntity<ExceptionDto> response= new ResponseEntity<>( exceptionDto, HttpStatus.NOT_FOUND);
//        return response;
//    }

}

