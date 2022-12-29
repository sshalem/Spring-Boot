package com.cachelevel1.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cachelevel1.entity.Product;
import com.cachelevel1.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional
    public Product getById(long id) {
	System.out.println("getById - First time - from database");
	Optional<Product> productResponse = productRepository.findById(id);
	System.out.println("getById - Second time - from cache");
	productResponse = productRepository.findById(id);
	System.out.println("getById - Third time - from cache");
	productResponse = productRepository.findById(id);
	System.out.println("getById - Fourth time - from cache");
	productResponse = productRepository.findById(id);
	Product product = productResponse.get();
	return product;
    }

    @Override
    @Transactional
    public Product getProductById(long id) {
	System.out.println("getProductById - First time - from database");
	Product productResponse = productRepository.findProductById(id);
	System.out.println("getProductById - Second time - from cache");
	productResponse = productRepository.findProductById(id);
	System.out.println("getProductById - Third time - from cache");
	productResponse = productRepository.findProductById(id);
	System.out.println("getProductById - Fourth time - from cache");
	productResponse = productRepository.findProductById(id);

	return productResponse;
    }

    @Override
    @Transactional
    public Product getProductByProductName(String productName) {
	System.out.println("getProductByProductName - First time - from database");
	Product productResponse = productRepository.findProductByProductName(productName);
	System.out.println("getProductByProductName - Second time - from cache");
	productResponse = productRepository.findProductByProductName(productName);
	System.out.println("getProductByProductName - Third time - from cache");
	productResponse = productRepository.findProductByProductName(productName);
	System.out.println("getProductByProductName - Fourth time - from cache");
	productResponse = productRepository.findProductByProductName(productName);
	return productResponse;
    }
}
