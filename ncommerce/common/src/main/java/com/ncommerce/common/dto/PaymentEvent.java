package com.ncommerce.common.dto;

import com.ncommerce.common.constant.PaymentStatus;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaymentEvent extends Notification {
    private Long orderId;
    private PaymentStatus status;
}
