package com.Charan.ProductServiceEcom.Services;

import com.Charan.ProductServiceEcom.Exceptions.CategoryNotFoundException;
import com.Charan.ProductServiceEcom.Exceptions.ProductNotFoundException;
import com.Charan.ProductServiceEcom.Models.Category;
import com.Charan.ProductServiceEcom.Models.Product;
import com.Charan.ProductServiceEcom.Repository.CategoryRepository;
import com.Charan.ProductServiceEcom.Repository.ProductRepository;
import com.Charan.ProductServiceEcom.dtos.SendEmailEventDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("OwnDBProductService")
public class OwnDBProductService implements ProductService {

    private  ProductRepository productRepository;

    private  CategoryRepository categoryRepository;

    private  KafkaTemplate<String, String> kafkaTemplate;

    private ObjectMapper objectMapper;

    public OwnDBProductService(ProductRepository productRepository,
                               CategoryRepository categoryRepository,
                               KafkaTemplate kafkaTemplate,
                               ObjectMapper objectMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public ResponseEntity<Product> getSingleProduct(Long id) throws ProductNotFoundException {

        Optional<Product> productOptional = productRepository.findById(id);

        if (productOptional.isEmpty()) {
            throw new ProductNotFoundException("Product with the given id " + id + "is not found" , "Please enter a valid id");
        }

        Product product = productOptional.get();

        return new ResponseEntity<>(product, HttpStatus.OK);
    }


    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product deleteProduct(Long id) throws ProductNotFoundException {

        Optional<Product> productOptional = productRepository.findById(id);

        if (productOptional.isEmpty()) {
            throw new ProductNotFoundException("Product with the given id " + id + "is not found" , "Please enter a valid id");
        }

        Product product = productOptional.get();

        productRepository.deleteById(id);


        return product;
    }

    @Override
    public Product updateProduct(Long productId, Product product) throws ProductNotFoundException {

        Optional<Product> productOptional = productRepository.findById(productId);

        if (productOptional.isEmpty()) {
            throw new ProductNotFoundException("Product with the give id " + productId + " is not found ", "Plese entera  exsisting id");
        }

        Product productInDB = productOptional.get();

        if(product.getTitle()!=null) {
            productInDB.setTitle(product.getTitle());
        }
        if(product.getDescription()!=null) {
            productInDB.setDescription(product.getDescription());
        }
        if(product.getPrice()!=null) {
            productInDB.setPrice(product.getPrice());
        }
        if(product.getCategory()!=null) {
            productInDB.setCategory(product.getCategory());
        }

        return productRepository.save(productInDB);
    }

    @Override
    public Product replaceProduct(Long productId, Product product) throws ProductNotFoundException, CategoryNotFoundException {

        Optional<Product> productOptional = productRepository.findById(productId);

        Product createdProduct;

        if (productOptional.isEmpty()) {
            createdProduct = new Product();
            createdProduct.setId(productId);
        }else{
            createdProduct = productOptional.get();
        }


        if(product.getTitle()!=null) {
            createdProduct.setTitle(product.getTitle());
        }
        if(product.getDescription()!=null) {
            createdProduct.setDescription(product.getDescription());
        }
        if(product.getPrice()!=null) {
            createdProduct.setPrice(product.getPrice());
        }

        Category categoryInDB;

        if(product.getCategory()!=null) {
            categoryInDB = categoryRepository.findByName(product.getCategory().getName());
            if (categoryInDB != null) {
                product.setCategory(categoryInDB);
            } else {
                Category newCategory = new Category();
                newCategory.setName(product.getCategory().getName());
                product.setCategory(newCategory);
            }
        }else{ // if parameters doesnt have category object
            throw new CategoryNotFoundException("Catefory is not found in the passed parameters","plaese send the category object");
        }


        return productRepository.save(createdProduct);
    }


    @Override
    public Product createProduct(String title, double price, String description, String category) {
        Product product = new Product();
        product.setTitle(title);
        product.setPrice(price);
        product.setDescription(description);

        Category categoryFromDb = categoryRepository.findByName(category);

        if (categoryFromDb == null) {
            Category newCategory = new Category();
            newCategory.setName(category);
            categoryFromDb= newCategory;
        }

        product.setCategory(categoryFromDb);

        return productRepository.save(product);
    }

    @Override
    public List<String> getAllCategories() {
       List<Category> allCategories = categoryRepository.findAll();
       List<String> categories = new ArrayList<>();
       for(Category category:allCategories){
           categories.add(category.getName());
       }
        return categories;
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findAllByCategoryName(category);
    }

    @Override
    public Page<Product> getAllProducts(int pageNumber, int pageSize , String sortBy) {
        return productRepository.findAll(PageRequest.of(pageNumber, pageSize,Sort.by(sortBy)));
    }

    @Override
    public Product emailDeletedProduct(Long id, SendEmailEventDto eventDto) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepository.findById(id);

        if (productOptional.isEmpty()) {
            throw new ProductNotFoundException("Product with the given id " + id + "is not found" , "Please enter a valid id");
        }

        Product product = productOptional.get();

        productRepository.deleteById(id);

        //sending email after deleting the product

        SendEmailEventDto emailEventDto = new SendEmailEventDto();
            emailEventDto.setTo(eventDto.getTo());
            emailEventDto.setFrom("charansaianisetti3@@gmail.com");
            emailEventDto.setSubject("Welcome to John Wick Ecommerce");
            emailEventDto.setBody("Hi Bhargaviii,Happy to have you on board. Explore-Experience and Experiment. Have a nice day!");

            try {
                kafkaTemplate.send(
                        "sendEmail",
                        objectMapper.writeValueAsString(emailEventDto)
                );
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

        return product;
    }


}
