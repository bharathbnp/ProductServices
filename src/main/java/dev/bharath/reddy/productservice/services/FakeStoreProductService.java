package dev.bharath.reddy.productservice.services;

import dev.bharath.reddy.productservice.dtos.FakeStoreProductDto;
import dev.bharath.reddy.productservice.models.Product;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService {


    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getSingleProduct(Long productId) {

        FakeStoreProductDto fakeStoreProduct = restTemplate.getForObject("https://fakestoreapi.com/products/" + productId, FakeStoreProductDto.class);

        // Make API call to fakestore API and return the response in product class way


        return fakeStoreProduct.toProductConversion();
    }

    @Override
    public List<Product> getAllProduct() {

        HttpEntity<FakeStoreProductDto> request = new HttpEntity<>(new FakeStoreProductDto());
        ResponseEntity<List<FakeStoreProductDto>> response = restTemplate.exchange(
                "https://fakestoreapi.com/products",
                HttpMethod.GET,
                request,
                new ParameterizedTypeReference<List<FakeStoreProductDto>>() {
                });
        List<Product> products = new ArrayList<>();

        for (FakeStoreProductDto product : response.getBody()) {
            Product prod = product.toProductConversion();
            products.add(prod);

        }
        return products;
    }

    @Override
    public List<Product> getProductInSpecificCategory(String category) {

        HttpEntity<FakeStoreProductDto> request = new HttpEntity<>(new FakeStoreProductDto());
        ResponseEntity<List<FakeStoreProductDto>> response = restTemplate.exchange("https://fakestoreapi.com/products/category/" + category,
                HttpMethod.GET,
                request,
                new ParameterizedTypeReference<List<FakeStoreProductDto>>() {
                });
        List<Product> products = new ArrayList<>();

        for (FakeStoreProductDto product : response.getBody()) {
            Product prod = product.toProductConversion();
            products.add(prod);

        }
        return products;
    }


    @Override
    public Product createProduct(String title, double price, String category, String description, String image) {

        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();

        fakeStoreProductDto.setTitle(title);
        fakeStoreProductDto.setImage(image);
        fakeStoreProductDto.setDescription(description);
        fakeStoreProductDto.setPrice(price);
        fakeStoreProductDto.setCategory(category);

        FakeStoreProductDto fakeStoreProduct = restTemplate.postForObject("https://fakestoreapi.com/products", fakeStoreProductDto, FakeStoreProductDto.class);
        return fakeStoreProduct.toProductConversion();
    }

    @Override
    public Product deleteProduct(Long id) {

        ResponseEntity<FakeStoreProductDto> response = restTemplate.exchange("https://fakestoreapi.com/products/" + id, HttpMethod.DELETE, null, FakeStoreProductDto.class);
        Product deletedProduct = response.getBody().toProductConversion();
        return deletedProduct;
    }

    @Override
    public Product updateProduct(Long id, String title, double price, String category, String description, String image) {

        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(title);
        fakeStoreProductDto.setImage(image);
        fakeStoreProductDto.setDescription(description);
        fakeStoreProductDto.setPrice(price);
        fakeStoreProductDto.setCategory(category);


        HttpEntity<FakeStoreProductDto> requestEntity = new HttpEntity<>(fakeStoreProductDto);
        ResponseEntity<FakeStoreProductDto> response = restTemplate.exchange("https://fakestoreapi.com/products/" + id, HttpMethod.PUT, requestEntity, FakeStoreProductDto.class);


        return response.getBody().toProductConversion();
    }

    @Override
    public List<String> getAllCategories() {

        HttpEntity<String> entity = new HttpEntity<>("parameters");

        // Make a GET request to the endpoint
        String url = "https://fakestoreapi.com/products/categories";
        ResponseEntity<List<String>> response = restTemplate.exchange(
                url, HttpMethod.GET, entity,
                new ParameterizedTypeReference<List<String>>() {
                }
        );

        // Extract the array of strings from the response
        List<String> strings = response.getBody();

        return strings;
    }
}
