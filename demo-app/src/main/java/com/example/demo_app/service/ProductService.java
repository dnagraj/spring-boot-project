package com.example.demo_app.service;

import com.example.demo_app.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ProductService {

    private final AtomicLong idGenerator = new AtomicLong(1);

    private final List<Product> products = new ArrayList<>();

    public List<Product> getAllProducts() {
        return products;
    }

    public Product getProductById(Long id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Product createProduct(Product product) {

        product.setId(idGenerator.getAndIncrement());

        products.add(product);

        return product;
    }

    public Product updateProduct(Long id, Product updatedProduct) {

        Product existingProduct = getProductById(id);

        if (existingProduct == null) {
            return null;
        }

        existingProduct.setName(updatedProduct.getName());
        existingProduct.setPrice(updatedProduct.getPrice());

        return existingProduct;
    }

    public boolean deleteProduct(Long id) {

        return products.removeIf(product ->
                product.getId().equals(id));
    }
}