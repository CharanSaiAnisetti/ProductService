package com.Charan.ProductServiceEcom.Services;

import com.Charan.ProductServiceEcom.Exceptions.ProductNotFoundException;
import com.Charan.ProductServiceEcom.Models.Category;
import com.Charan.ProductServiceEcom.Models.Product;
import com.Charan.ProductServiceEcom.dtos.FakeStoreProductDto;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
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
    public ResponseEntity<Product> getSingleProduct(Long productId) throws ProductNotFoundException {

       FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject(
               "https://fakestoreapi.com/products/"+ productId,//url of the third party
                FakeStoreProductDto.class//type of class which want ot convert the server response
        );

        if(fakeStoreProductDto==null){
            throw new ProductNotFoundException("The Product with the given id " + "'"+ productId + "'" + " is not found please try with other id",
                                                "Please enter a valid product id in the range 1 - 20");
        }

       Product product = convertFakeStoreProductDtoToProduct(fakeStoreProductDto);



       ResponseEntity<Product> response = new ResponseEntity<>(product, HttpStatus.OK);


        return response;
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
    public Product deleteProduct(Long id) throws ProductNotFoundException {

        ResponseEntity<Product> productResponseEntity = getSingleProduct(id);
        Product product = productResponseEntity.getBody();
        restTemplate.delete("https://fakestoreapi.com/products/"+ id,
                FakeStoreProductDto.class);//restTemplate.delete() return type if void


          return product;
    }


    //update API will not work for Fake Store reason --> Fake Store server side error
    // this method will return a HTTP --> 500 response
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

    @Override
    public List<Product> getProductsByCategory(String category) {

        FakeStoreProductDto[] fakeStoreProductDto = restTemplate.getForObject("https://fakestoreapi.com/products/category/"+category,
                                                                                    FakeStoreProductDto[].class);
        List<Product> products = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDtos : fakeStoreProductDto){
            products.add(convertFakeStoreProductDtoToProduct(fakeStoreProductDtos));
        }
        return products;
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
