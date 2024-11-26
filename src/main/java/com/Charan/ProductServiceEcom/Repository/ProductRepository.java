package com.Charan.ProductServiceEcom.Repository;

import com.Charan.ProductServiceEcom.Models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findById(long id);


    @Override
    @Query(value = "select id,title from product",nativeQuery = true)
    List<Product> findAll(Sort sort);

    void deleteById(long id);
    
    Product save(Product product);

    List<Product> findAllByCategoryName(String category);

    Page<Product> findAll(Pageable pageable);

}
