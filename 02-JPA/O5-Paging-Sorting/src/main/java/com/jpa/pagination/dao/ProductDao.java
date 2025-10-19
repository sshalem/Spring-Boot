package com.jpa.pagination.dao;

import java.util.List;

import org.springframework.data.domain.Page;

import com.jpa.pagination.entity.ProductEntity;

public interface ProductDao {

	List<ProductEntity> getAllProducts();

	List<ProductEntity> findProductWithSorting(String field);

	List<ProductEntity> getProductsByPageAndSize(int page, int size);
	
	Page<ProductEntity> getProductsByPageAndSizeAndReturnAsPage(int page, int size);

	List<ProductEntity> getProductsWithPriceLessThan(long price, int page, int size, String field);
}
