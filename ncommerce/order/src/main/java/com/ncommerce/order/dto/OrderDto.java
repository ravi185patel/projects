package com.ncommerce.order.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
@Data
@ToString
public class OrderDto {

    private Long id;
    private String productId;
    private String userId;
    private int quantity;
    private double price;

}
