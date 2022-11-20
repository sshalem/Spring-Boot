package com.jpa.pagination.dao;

import java.util.List;

import com.jpa.pagination.entity.ProductEntity;

public interface ProductDao {

	List<ProductEntity> getAllProducts();

	List<ProductEntity> findProductWithSorting(String field);
	
	List<ProductEntity> getProductsByPageAndSize(int page, int size);
}
