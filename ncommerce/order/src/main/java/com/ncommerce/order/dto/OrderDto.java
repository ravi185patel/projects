package com.ncommerce.order.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
@Data
public class OrderDto {

    private Long id;
    private String productId;
    private String userId;
    private int quantity;
    private double price;

}
