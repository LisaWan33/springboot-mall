package com.lisawan.springbootmall.dao;

import com.lisawan.springbootmall.dto.ProductRequest;
import com.lisawan.springbootmall.model.Product;

import java.util.List;

public interface ProductDao {
    List<Product> getProducts();

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProduct(Integer productId);
}
