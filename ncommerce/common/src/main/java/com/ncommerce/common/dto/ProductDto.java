package com.ncommerce.common.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
@Data
@ToString
public class ProductDto {
    private String id;
    private String productName;
    private String productCode;
    private int stock;
    private double price;

}
