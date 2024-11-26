package com.Charan.ProductServiceEcom.Controllers;

import com.Charan.ProductServiceEcom.ElasticSearch.ProductDocument;
import com.Charan.ProductServiceEcom.Services.ElasticSearchProductService;
import org.apache.http.nio.entity.NStringEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ES/products")
public class ElasticSearchController {

    private ElasticSearchProductService elasticSearchProductService;

    public ElasticSearchController(ElasticSearchProductService elasticSearchProductService) {
        this.elasticSearchProductService = elasticSearchProductService;
    }

    @PostMapping
    public ProductDocument addProduct(@RequestBody ProductDocument productDocument) {
        return elasticSearchProductService.addProduct(productDocument);
    }

    @GetMapping
    public Iterable<ProductDocument> getProducts() {
        return elasticSearchProductService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Optional<ProductDocument> getProduct(@PathVariable("id") String id) {
        return elasticSearchProductService.getProduct(id);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") String id) {
        elasticSearchProductService.deleteProduct(id);
    }
    @DeleteMapping
    public void deleteAllProducts() {
        elasticSearchProductService.deleteAllProducts();
    }

    @PostMapping("/data")
    public String generateProducts(){
        elasticSearchProductService.saveBulkProducts();
        return "Products added successfully";
    }
}
