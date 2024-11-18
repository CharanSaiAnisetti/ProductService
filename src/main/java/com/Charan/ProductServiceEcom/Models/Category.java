package com.Charan.ProductServiceEcom.Models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@JsonPropertyOrder({"id","name", "description","createdAt", "updatedAt"}) // Defining the desired response output order
public class Category  extends BaseModel implements Serializable {


    private String name;
    private String description;

}
