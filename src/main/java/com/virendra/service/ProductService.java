package com.virendra.service;

import com.virendra.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    void addProduct(Product product);
    Optional<Product> updateProduct(Product product);
    Product findById(Integer id);
    boolean deleteProductById(Integer id);
    List<Product> getProductByName(String productName);
    List<Product> getAllProducts(String key, String sortDir);
}
