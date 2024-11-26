package com.Charan.ProductServiceEcom.ElasticSearch;


import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "products")
public class ProductDocument implements Serializable {

    @Id
    String id;
    String title;
    String description;
    String price;
    String category;

}
