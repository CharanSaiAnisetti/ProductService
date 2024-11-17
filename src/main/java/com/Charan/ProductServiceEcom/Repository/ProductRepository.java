package com.Charan.ProductServiceEcom.Repository;

import com.Charan.ProductServiceEcom.Models.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findById(long id);


    @Override
    List<Product> findAll(Sort sort);

    Product deleteById(long id);

    Product save(Product product);
}
