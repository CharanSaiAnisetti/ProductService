package com.Charan.ProductServiceEcom.Services;

import com.Charan.ProductServiceEcom.ElasticSearch.ProductDocument;
import com.Charan.ProductServiceEcom.Models.Product;
import com.Charan.ProductServiceEcom.Repository.ElasticSearchProductRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ElasticSearchProductService {

    private ElasticSearchProductRepository elasticSearchProductRepository;

    public ElasticSearchProductService(ElasticSearchProductRepository elasticSearchProductRepository) {
        this.elasticSearchProductRepository = elasticSearchProductRepository;
    }

    public ProductDocument addProduct(ProductDocument productDocument) {
        return elasticSearchProductRepository.save(productDocument);
    }

    public Iterable<ProductDocument> getAllProducts() {
        return elasticSearchProductRepository.findAll();
    }

    public Optional<ProductDocument> getProduct(String id) {
        return elasticSearchProductRepository.findById(id);
    }

    public void deleteProduct(String id) {
        elasticSearchProductRepository.deleteById(id);
    }

    public void deleteAllProducts() {
        elasticSearchProductRepository.deleteAll();
    }

    List<ProductDocument> productDocumentList = Arrays.asList(
            // Mobiles
            new ProductDocument("1", "Galaxy S21", "Flagship smartphone with 5G and 128GB storage", "799.99", "Mobiles"),
            new ProductDocument("2", "iPhone 13", "128GB storage, dual-camera, 5G connectivity", "899.99", "Mobiles"),
            new ProductDocument("3", "OnePlus 9", "5G smartphone with 256GB storage and 12GB RAM", "699.99", "Mobiles"),
            new ProductDocument("4", "Pixel 6", "Android flagship with exceptional camera performance", "599.99", "Mobiles"),
            new ProductDocument("5", "Xiaomi Redmi Note 10", "Affordable 5G smartphone with 64GB storage", "299.99", "Mobiles"),
            new ProductDocument("6", "Huawei P40", "High-performance smartphone with Leica camera", "649.99", "Mobiles"),

            // Laptops
            new ProductDocument("7", "MacBook Pro 13", "M1 chip, 256GB SSD, Retina display", "1299.99", "Laptops"),
            new ProductDocument("8", "Dell XPS 15", "Core i7, 512GB SSD, 16GB RAM", "1599.99", "Laptops"),
            new ProductDocument("9", "HP Spectre x360", "Convertible laptop with 1TB SSD", "1499.99", "Laptops"),
            new ProductDocument("10", "Lenovo ThinkPad X1", "Business laptop with 256GB SSD", "1399.99", "Laptops"),
            new ProductDocument("11", "Asus ROG Zephyrus", "Gaming laptop with RTX 3080", "1899.99", "Laptops"),
            new ProductDocument("12", "Acer Swift 3", "Budget-friendly with 512GB SSD", "799.99", "Laptops"),

            // Watches
            new ProductDocument("13", "Apple Watch Series 7", "Smartwatch with health tracking", "399.99", "Watches"),
            new ProductDocument("14", "Samsung Galaxy Watch 4", "Android smartwatch with fitness features", "349.99", "Watches"),
            new ProductDocument("15", "Garmin Fenix 6", "Multisport GPS watch with maps", "499.99", "Watches"),
            new ProductDocument("16", "Fitbit Versa 3", "Fitness tracker with heart rate monitor", "199.99", "Watches"),
            new ProductDocument("17", "Fossil Gen 5", "Classic design with smart features", "299.99", "Watches"),
            new ProductDocument("18", "Casio G-Shock", "Rugged outdoor digital watch", "129.99", "Watches"),

            // Shoes
            new ProductDocument("19", "Nike Air Max", "Comfortable running shoes", "149.99", "Shoes"),
            new ProductDocument("20", "Adidas Ultraboost", "High-performance running shoes", "179.99", "Shoes"),
            new ProductDocument("21", "Puma RS-X", "Stylish casual sneakers", "119.99", "Shoes"),
            new ProductDocument("22", "Reebok Nano X", "Cross-training shoes", "139.99", "Shoes"),
            new ProductDocument("23", "New Balance 574", "Classic retro design sneakers", "99.99", "Shoes"),
            new ProductDocument("24", "Under Armour HOVR", "Energy-returning running shoes", "159.99", "Shoes"),

            // Jackets
            new ProductDocument("25", "North Face Insulated Jacket", "Waterproof winter jacket", "199.99", "Jackets"),
            new ProductDocument("26", "Patagonia Down Sweater", "Lightweight, packable down jacket", "249.99", "Jackets"),
            new ProductDocument("27", "Columbia Rain Jacket", "Breathable and waterproof", "119.99", "Jackets"),
            new ProductDocument("28", "Arc'teryx Beta AR", "Premium outdoor jacket for harsh conditions", "399.99", "Jackets"),
            new ProductDocument("29", "Levi's Denim Jacket", "Classic denim jacket", "89.99", "Jackets"),
            new ProductDocument("30", "Barbour Waxed Jacket", "Water-resistant casual wear", "299.99", "Jackets")
    );


    //save all the product to the elastic search at once
    public void saveBulkProducts() {
        elasticSearchProductRepository.saveAll(productDocumentList);
    }


}
