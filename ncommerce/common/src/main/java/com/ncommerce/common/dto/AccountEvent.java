package com.ncommerce.common.dto;

import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AccountEvent extends Notification {
    private Long userId;
    private LocalDate publishDate;
}
