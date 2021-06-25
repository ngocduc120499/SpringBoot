package com.example.demo.dao;

import com.example.demo.model.Product;

import java.util.List;

public interface ProductDAO {
    Product createProduct(Product product);
    Product updateProduct(Product product);
    List<Product> getAllProduct();
    Product getProductByID(long productId);
    void deleteProduct(long productId);
}
