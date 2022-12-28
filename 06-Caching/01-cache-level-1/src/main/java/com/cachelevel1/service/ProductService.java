package com.cachelevel1.service;

import com.cachelevel1.entity.Product;

public interface ProductService {

    Product getById(long id);

    Product getProductById(long id);

    Product getProductByProductName(String productName);
}
