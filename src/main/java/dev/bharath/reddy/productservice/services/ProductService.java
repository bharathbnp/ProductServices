package dev.bharath.reddy.productservice.services;

import dev.bharath.reddy.productservice.models.Product;

import java.util.List;

public interface ProductService {

    Product getSingleProduct(Long ProductId);

    List<Product> getAllProduct();


    List<Product> getProductInSpecificCategory(String category);

    Product createProduct(String title, double price, String category, String description, String image);

    Product deleteProduct(Long id);


    Product updateProduct(Long id, String title, double price, String category, String description, String image);


    List<String> getAllCategories();
}
