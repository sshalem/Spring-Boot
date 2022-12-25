package com.cachelevel1.controller;

import com.cachelevel1.entity.Product;
import com.cachelevel1.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/level-1")
public class CacheLevel1Controller {

    @Autowired
    private ProductServiceImpl productService;

    @GetMapping(path = "/{id}")
    public Product getProductById(@PathVariable("id") long id) {
        return productService.getProductById(id);
    }
}
