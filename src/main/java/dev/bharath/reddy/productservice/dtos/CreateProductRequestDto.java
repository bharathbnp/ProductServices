package dev.bharath.reddy.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductRequestDto {

//    private Long id;
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;
}
