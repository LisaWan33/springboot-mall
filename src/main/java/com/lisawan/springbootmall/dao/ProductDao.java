package com.lisawan.springbootmall.dao;

import com.lisawan.springbootmall.dto.ProductRequest;
import com.lisawan.springbootmall.model.Product;

public interface ProductDao {
    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);
}
