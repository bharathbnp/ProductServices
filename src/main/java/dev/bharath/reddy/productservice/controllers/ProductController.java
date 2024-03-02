package dev.bharath.reddy.productservice.controllers;


import dev.bharath.reddy.productservice.dtos.CreateProductRequestDto;
import dev.bharath.reddy.productservice.models.Product;
import dev.bharath.reddy.productservice.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {


    private ProductService productService;


    public ProductController(ProductService productService) {

        this.productService = productService;
    }

    //POST  /products
    //Request Bodyy
//    {
//        title:
//        description:
//        price:
//    }
    @PostMapping("/products")
    public Product createProduct(@RequestBody CreateProductRequestDto createProductRequestDto) {
        Product returnProuduct = productService.createProduct(
                createProductRequestDto.getTitle(),
                createProductRequestDto.getPrice(),
                createProductRequestDto.getCategory(),
                createProductRequestDto.getDescription(),
                createProductRequestDto.getImage());
        return returnProuduct;
    }

    @PutMapping("products/{id}")
    public Product deleteProduct(
            @PathVariable("id") Long id,
            @RequestBody CreateProductRequestDto createProductRequestDto) {
        Product returnProuduct = productService.updateProduct(
                id,
                createProductRequestDto.getTitle(),
                createProductRequestDto.getPrice(),
                createProductRequestDto.getCategory(),
                createProductRequestDto.getDescription(),
                createProductRequestDto.getImage()
        );
        return returnProuduct;

    }


    @GetMapping("/products/{id}")
    public Product getProductDetails(@PathVariable("id") Long productId) {
        return productService.getSingleProduct(productId);
    }

    @GetMapping("products/category/{name}")
    public List<Product> getProductsInCateogory(@PathVariable("name") String name) {
        return productService.getProductInSpecificCategory(name);
    }

    @GetMapping("/products")
    public List<Product> getAllProduct() {
        return productService.getAllProduct();
    }

    @GetMapping("/products/categories")
    public List<String> getAllCategories() {

        return productService.getAllCategories();
    }

    @DeleteMapping("/products/{id}")
    public Product deleteProduct(@PathVariable("id") Long productId) {
        return productService.deleteProduct(productId);
    }
}
