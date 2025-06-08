package com.ncommerce.user.dto;

import com.ncommerce.user.constant.Role;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Data
@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String email;
    private String password;
    private String name;
    private boolean active = true;  // For soft delete
    private Role role = Role.USER;  // USER or ADMIN
}
