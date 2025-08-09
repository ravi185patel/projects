package com.ncommerce.common.dto;


import com.ncommerce.common.constant.OrderStatus;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderEvent extends Notification {
    private OrderStatus status;
}
