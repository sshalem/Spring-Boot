package com.cachelevel1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cachelevel1.entity.Product;
import com.cachelevel1.service.ProductServiceImpl;

@RestController
@RequestMapping("/level-1")
public class CacheLevel1Controller {

    @Autowired
    private ProductServiceImpl productService;

    @GetMapping(path = "/getById/{id}")
    public Product getById(@PathVariable("id") long id) {
	System.out.println("<<<<<<<<<<------------------->>>>>>>>>>> \n");
	return productService.getById(id);
    }
    
    @GetMapping(path = "/getProductById/{id}")
    public Product getProductById(@PathVariable("id") long id) {
	System.out.println("<<<<<<<<<<------------------->>>>>>>>>>> \n");
	return productService.getProductById(id);
    }
    
    @GetMapping(path = "getProductByProductName/{productName}")
    public Product getProductByProductName(@PathVariable("productName") String productName) {
	System.out.println("<<<<<<<<<<------------------->>>>>>>>>>> \n");
	return productService.getProductByProductName(productName);
    }
}
