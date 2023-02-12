package com.lisawan.springbootmall.controller;

import com.lisawan.springbootmall.constant.ProductCategory;
import com.lisawan.springbootmall.dto.ProductQueryParams;
import com.lisawan.springbootmall.dto.ProductRequest;
import com.lisawan.springbootmall.model.Product;
import com.lisawan.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    //查詢所有商品類表
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getproducts(
            //查詢條件 Filtering
            @RequestParam(required = false) ProductCategory category,
            @RequestParam(required = false) String search,

            //排序Sorting
            //(更改預設為使用創建時間來排序，且為降序)
            @RequestParam(defaultValue ="created_date") String orderBy,
            @RequestParam(defaultValue = "desc") String sort
    ){
        ProductQueryParams productQueryParams = new ProductQueryParams();
        productQueryParams.setCategory(category);
        productQueryParams.setSearch(search);
        productQueryParams.setOrderBy(orderBy);
        productQueryParams.setSort(sort);

       List<Product> productList = productService.getProducts(productQueryParams);

       return ResponseEntity.status(HttpStatus.OK).body(productList);

    }




    //查詢單個商品數據
    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId){
        Product product = productService.getProductById(productId);
        if( product != null ){
            return ResponseEntity.status(HttpStatus.OK).body(product);

        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    //新增單個商品數據
    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductRequest productRequest){

//        System.out.println(productRequest.getProductName());

        Integer productId = productService.createProduct(productRequest);

        Product product =productService.getProductById(productId);

        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    //更新單個商品數據
    @PutMapping("/products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer productId,
                                                 @RequestBody @Valid ProductRequest productRequest){

        //檢查 product 是否存在
        Product product = productService.getProductById(productId);
        if (product == null) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }

        //修改商品的數據
        productService.updateProduct(productId,productRequest);

        Product updateProduct = productService.getProductById(productId);

        return ResponseEntity.status(HttpStatus.OK).body(updateProduct);

    }

    //刪除單個商品數據
    //商品存在，成功刪掉；商品本就不存在
    @DeleteMapping("/products/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer productId){

        productService.deleteProductById(productId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }


}
