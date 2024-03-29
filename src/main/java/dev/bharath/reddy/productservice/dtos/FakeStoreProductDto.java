package dev.bharath.reddy.productservice.dtos;


import dev.bharath.reddy.productservice.models.Category;
import dev.bharath.reddy.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {

    private Long id;
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;


    public Product toProductConversion() {
        Product product = new Product();

        product.setId(id);
        product.setTitle(title);
        product.setPrice(price);
        product.setDescription(description);
        product.setImageUrl(image);


        Category productCategory = new Category();
        productCategory.setTitle(category);

        product.setCategory(productCategory);

        return product;

    }

}
