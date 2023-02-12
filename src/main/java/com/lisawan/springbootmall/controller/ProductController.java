package com.lisawan.springbootmall.controller;

import com.lisawan.springbootmall.model.Product;
import com.lisawan.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;


    //取得某一商品數據
    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId){
        Product product = productService.getProductById(productId);
        if( product != null ){
            return ResponseEntity.status(HttpStatus.OK).body(product);

        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
