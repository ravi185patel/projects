package com.ncommerce.order.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class OrderDto {

    private Long id;
    private Long userId; // just the ID, not a User object
    private Long shippingAddressId;
    private Long billingAddressId;
    private LocalDateTime orderDate;
    private String status;
    private Double totalAmount;
    private String paymentMethod;
    private String paymentStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}


