package com.example.demo_app.service;

import com.example.demo_app.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    private ProductService productService;

    @BeforeEach
    void setUp() {
        productService = new ProductService();
    }

    @Test
    void shouldCreateProduct() {

        Product product =
                new Product(null, "Kubernetes Course", 99.99);

        Product created =
                productService.createProduct(product);

        assertNotNull(created.getId());
        assertEquals("Kubernetes Course", created.getName());
        assertEquals(99.99, created.getPrice());
    }

    @Test
    void shouldReturnAllProducts() {

        productService.createProduct(
                new Product(null, "Docker", 49.99));

        productService.createProduct(
                new Product(null, "Kubernetes", 99.99));

        List<Product> products =
                productService.getAllProducts();

        assertEquals(2, products.size());
    }

    @Test
    void shouldFindProductById() {

        Product created =
                productService.createProduct(
                        new Product(null, "DevOps", 149.99));

        Product found =
                productService.getProductById(created.getId());

        assertNotNull(found);
        assertEquals("DevOps", found.getName());
    }

    @Test
    void shouldDeleteProduct() {

        Product created =
                productService.createProduct(
                        new Product(null, "Docker", 49.99));

        boolean deleted =
                productService.deleteProduct(created.getId());

        assertTrue(deleted);
        assertNull(
                productService.getProductById(created.getId()));
    }
}