package com.ncommerce.common.eventdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderCreatedEvent {

    private String orderId;
    private String userId;
    private Double amount;

}


