package com.ncommerce.common.eventdto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InventoryFailedEvent {
    private String orderId;
}
