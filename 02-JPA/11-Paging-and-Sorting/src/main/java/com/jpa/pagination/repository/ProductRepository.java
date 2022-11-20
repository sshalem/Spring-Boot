package com.jpa.pagination.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jpa.pagination.entity.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

	@Query(value = "SELECT * FROM product_tb p WHERE p.price <= :price", 
			countQuery = "SELECT COUNT(*) FROM product_tb",
			nativeQuery = true)
	Page<ProductEntity> findProductsWithPriceLessThan(@Param("price") long price, Pageable pageable);
}
