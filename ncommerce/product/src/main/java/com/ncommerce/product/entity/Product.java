package com.ncommerce.product.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
@NoArgsConstructor
@Data
@Getter
@Setter
public class Product {

    @Id
    private String id;
    private String productName;
    private String productCode;
    private Double price;
    private int stock;
}
