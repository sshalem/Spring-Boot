package com.jpa.pagination.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.pagination.dao.ProductDaoImpl;
import com.jpa.pagination.dto.ApiResponse;
import com.jpa.pagination.entity.ProductEntity;

@RestController
@CrossOrigin("*")
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductDaoImpl productDaoImpl;

	@GetMapping(path = "/getAllProducts", produces = { 
			MediaType.APPLICATION_JSON_VALUE, 
			MediaType.APPLICATION_XML_VALUE })
	public ApiResponse<List<ProductEntity>> getCourses() {
		
		List<ProductEntity> _products = productDaoImpl.getAllProducts();
		
		return new ApiResponse<List<ProductEntity>>(_products.size(), _products);
	}
	
	/**
	 * the 'field' can be any of the entity variables:
	 * id, name , quantity, price
	 */
	@GetMapping(path = "/getProductsWithSorting/{field}", produces =  MediaType.APPLICATION_JSON_VALUE)
	public ApiResponse<List<ProductEntity>> findProductWithSorting(@PathVariable("field") String field) {
		
		List<ProductEntity> _productWithSorting = productDaoImpl.findProductWithSorting(field);		
		return new ApiResponse<List<ProductEntity>>(_productWithSorting.size(), _productWithSorting);
	}
	
	
	@GetMapping(path = "/getProductsByPagination", produces = { 
			MediaType.APPLICATION_JSON_VALUE, 
			MediaType.APPLICATION_XML_VALUE })
	public ApiResponse<List<ProductEntity>> getProductsByPageAndSize(
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "25") int size) {
		
		List<ProductEntity> _products = productDaoImpl.getProductsByPageAndSize(page, size);		
		return new ApiResponse<List<ProductEntity>>(_products.size(), _products);
	}
		  
	
	@GetMapping(path = "/getProductsWithPriceLessThan/{price}/{field}", produces = { 
			MediaType.APPLICATION_JSON_VALUE, 
			MediaType.APPLICATION_XML_VALUE })
	public ApiResponse<List<ProductEntity>> getProductsWithPriceLessThan(
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "25") int size,
			@PathVariable("price") long price,
			@PathVariable("field") String field) {
		
		List<ProductEntity> _products = productDaoImpl.getProductsWithPriceLessThan(price, page, size ,field);
		return new ApiResponse<List<ProductEntity>>(_products.size(), _products);
	}	
}
