package com.ncommerce.common.dto;


import com.ncommerce.common.constant.PaymentStatus;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {
    private Long paymentId;
    private String customerId;
    private Long orderId;
    private double payment;
    private PaymentStatus paymentStatus;
    private LocalDate paymentDate;
}
