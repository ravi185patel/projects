package com.ncommerce.order.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Data
@Getter
@Setter
@NoArgsConstructor
public class Order {

    @Id
    private String id;
    @Column(nullable = false)
    private String userId; // just the ID, not a User object
    @Column(nullable = false)
    private String shippingAddressId;
    @Column(nullable = false)
    private String billingAddressId;
    @Column(nullable = false)
    private LocalDateTime orderDate;
    @Column(nullable = false)
    private String status;
    @Column(nullable = false)
    private Double totalAmount;
    @Column(nullable = false)
    private String paymentMethod;
    @Column(nullable = false)
    private String paymentStatus;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false)
    private LocalDateTime updatedAt;
}


