package com.Charan.ProductServiceEcom.Services;

import com.Charan.ProductServiceEcom.Models.Category;
import com.Charan.ProductServiceEcom.Models.Product;
import com.Charan.ProductServiceEcom.dtos.FakeStoreProductDto;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService{

    RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getSingleProduct(Long productId) {
        // call the FakeStore product service to get the product with the given id;
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject(
               "https://fakestoreapi.com/products/"+ productId,//url of the third party
                FakeStoreProductDto.class//class type of object that the third party returns which resembles class in our code base
        );


        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
    }

    @Override
    public List<Product> getAllProducts() {
    FakeStoreProductDto[] fakeStoreProductDto = restTemplate.getForObject(
            "https://fakestoreapi.com/products",
            FakeStoreProductDto[].class
    );

     List<Product> products = new ArrayList<>();
    for(FakeStoreProductDto fakeStoreProductDtos : fakeStoreProductDto){
        products.add(convertFakeStoreProductDtoToProduct(fakeStoreProductDtos));
    }
        return products;
    }

    @Override
    public void deleteProduct(Long id) {

    }


    //update method is not working from the fakeStore Product server side so this method will return a HTTP --> 500 response
    @Override
    public Product updateProduct(Long productId, Product product) {
        RequestCallback requestCallback = restTemplate.httpEntityCallback(product,FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor = new HttpMessageConverterExtractor(FakeStoreProductDto.class, restTemplate.getMessageConverters());
        FakeStoreProductDto fakeStoreProductDto = restTemplate.execute("https://fakestoreapi.com/products/"+ productId ,
                HttpMethod.PATCH,
                requestCallback,
                responseExtractor);
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
    }

    @Override
    public Product replaceProduct(Long productId, Product product) {


        RequestCallback requestCallback = restTemplate.httpEntityCallback(product, FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = restTemplate.execute("https://fakestoreapi.com/products/"+ productId,
                HttpMethod.PUT,
                requestCallback,
                responseExtractor);

        FakeStoreProductDto fakeStoreProductDto = fakeStoreProductDtoResponseEntity.getBody();

        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);

    }

    @Override
    public Product createProduct(String title, Double price, String description, String image, String category) {

        FakeStoreProductDto fakestoreProductDto = new FakeStoreProductDto();
        fakestoreProductDto.setTitle(title);
        fakestoreProductDto.setPrice(price);
        fakestoreProductDto.setDescription(description);
        fakestoreProductDto.setImage(image);
        fakestoreProductDto.setCategory(category);

        FakeStoreProductDto response = restTemplate.postForObject("https://fakestoreapi.com/products",
                                                                    fakestoreProductDto,
                                                                    FakeStoreProductDto.class);

        return convertFakeStoreProductDtoToProduct(response);
    }


    @Override
    public List<String> getAllCategories() {
       String[] categories =  restTemplate.getForObject("https://fakestoreapi.com/products/categories",
                                        String[].class);

       List<String> categoryList = new ArrayList<>();
        Collections.addAll(categoryList, categories);
        return categoryList;
    }


    // method to convert FakeStoreProductDto to Product Object

    public Product convertFakeStoreProductDtoToProduct(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());

        Category category = new Category();
        category.setDescription(fakeStoreProductDto.getDescription());
        product.setCategory(category);

        return product;
    }
}
