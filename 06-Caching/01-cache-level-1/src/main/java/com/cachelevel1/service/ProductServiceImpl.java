package com.cachelevel1.service;

import com.cachelevel1.entity.Product;
import com.cachelevel1.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product getProductById(long id) {
        productRepository.findProductById(id);
        return productRepository.findProductById(id);
    }

    @Override
    public Product getProductByProductName(String productName) {
        return productRepository.findProductByProductName(productName);
    }
}
