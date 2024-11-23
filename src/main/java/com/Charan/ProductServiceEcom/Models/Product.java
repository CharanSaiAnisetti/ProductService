package com.Charan.ProductServiceEcom.Models;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

@Getter
@Setter
@Entity
@JsonPropertyOrder({"id", "title", "price", "description", "createdAt", "updatedAt","category"}) // Defining the desired order of output response
public class Product extends BaseModel implements Serializable {


    private String title;
    private Double price;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Category category;

}
