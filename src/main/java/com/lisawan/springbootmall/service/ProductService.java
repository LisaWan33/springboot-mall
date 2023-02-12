package com.lisawan.springbootmall.service;

import com.lisawan.springbootmall.dto.ProductRequest;
import com.lisawan.springbootmall.model.Product;

public interface ProductService {
    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);
}
