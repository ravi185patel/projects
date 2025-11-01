package com.ncommerce.common.eventdto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InventoryReservedEvent {
    private String orderId;
}
