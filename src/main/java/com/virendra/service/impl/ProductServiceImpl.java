package com.virendra.service.impl;

import com.virendra.repo.ProductRepository;
import com.virendra.entity.Product;
import com.virendra.service.ProductService;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import static io.quarkus.agroal.runtime.AgroalConnectionConfigurer.log;

@ApplicationScoped
public class ProductServiceImpl implements ProductService {

    @Inject
    ProductRepository productRepository;

    @Transactional
    @Override
    public void addProduct(Product product) {
        productRepository.persist(product);
    }

    @Override
    @Transactional
    public Optional<Product> updateProduct(Product product) {
        log.info("Updating product");

        Optional<Product> updatedProduct = productRepository.findByIdOptional(product.id)
                .map(p -> {
                    p.setName(product.getName());
                    p.setDescription(product.getDescription());
                    p.setPrice(product.getPrice());
                    p.setQuantity(product.getQuantity());
                    log.info("Updating product "+ p);
                    return p;
                });

        return updatedProduct;
    }

    @Override
    public Product findById(Integer id) {
        return productRepository.findById(Long.valueOf(id));
    }

    @Transactional
    @Override
    public boolean deleteProductById(Integer id) {
        Product product = productRepository.findById(Long.valueOf(id));
        if (product != null) {
            return productRepository.deleteById(Long.valueOf(id));
        }
        return false;
    }

    @Override
    public List<Product> getProductByName(String productName) {
        List<Product> products = productRepository.findByName(productName);
        return products;
    }

    /*@Override
    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }*/

    @Override
    public List<Product> getAllProducts(String key, String sortDir) {
        Sort sort = Sort.by(key == null || key.isBlank() ? "id" : key);
        sortDir = sortDir == null || sortDir.isBlank() ? "asc" : sortDir;
        if(sortDir.equals("asc")) {
            sort = sort.ascending();
        }else if(sortDir.equals("desc")) {
            sort = sort.descending();
        }
        return productRepository.getAllProducts2(sort);
    }

    public List<Product> getAllProducts(Sort sort) {
        return productRepository.getAllProducts2(sort);
    }
}
