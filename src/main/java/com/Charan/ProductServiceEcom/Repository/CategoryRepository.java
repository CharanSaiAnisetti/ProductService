package com.Charan.ProductServiceEcom.Repository;

import com.Charan.ProductServiceEcom.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category save(Category category);

    Category findByName(String name);

    Category existsByName(String name);
}
