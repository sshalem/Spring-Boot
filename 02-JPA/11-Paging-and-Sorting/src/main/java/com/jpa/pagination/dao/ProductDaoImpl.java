package com.jpa.pagination.dao;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jpa.pagination.entity.ProductEntity;
import com.jpa.pagination.repository.ProductRepository;

@Service
public class ProductDaoImpl implements ProductDao {

	@Autowired
	private ProductRepository productRepository;

	@PostConstruct
	public void initDB() {
		
		List<ProductEntity> _products = 
				IntStream
					.rangeClosed(1, 200)
					.mapToObj(i -> new ProductEntity("product" + i, new Random().nextInt(100), new Random().nextInt(50_000)))
					.collect(Collectors.toList());
		
		productRepository.saveAll(_products);
	}

	@Override
	public List<ProductEntity> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public List<ProductEntity> findProductWithSorting(String field) {

		// the 'field' can be any of the entity variables: id, name , quantity, price		 
		List<ProductEntity> _listProductEntities = productRepository.findAll(Sort.by(Sort.Direction.ASC, field));
		return _listProductEntities;
	}

	@Override
	public List<ProductEntity> getProductsByPageAndSize(int page, int size) {
		/**
		 * Here I implement pagination , and get a: Limited Number of Courses per PAGE
		 * page: zero-based page index, must NOT be negative. size: number of items in a
		 * page to be returned, must be greater than 0. sort: the Sort object.
		 */
		if (page > 0) {
			page = page - 1;
		}	
		Pageable pageable = PageRequest.of(page, size);
		Page<ProductEntity> _pageOfProducts = productRepository.findAll(pageable);
		List<ProductEntity> _products = _pageOfProducts.getContent();
		return _products;
	}
	
	
	@Override
	public List<ProductEntity> getProductsWithPriceLessThan(long price, int page, int size, String field) {		
		if (page > 0) {
			page = page - 1;
		}			
		Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, field));		
		Page<ProductEntity> _pageOfProducts = productRepository.findProductsWithPriceLessThan(price, pageable);		
		List<ProductEntity> _products = _pageOfProducts.getContent();		
		return _products;
	}	

}
