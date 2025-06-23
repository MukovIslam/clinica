package com.clinic.auth_service.DTO;

import com.clinic.auth_service.Role;
import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private Role role;
}
