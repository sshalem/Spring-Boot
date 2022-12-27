package com.cachelevel1.repository;

import com.cachelevel1.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findProductById(long id);
    Product findProductByProductName(String productName);
}
