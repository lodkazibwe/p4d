package com.ruraaratech.p4dafrica.sysUser.security;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private final String jwt;
    private long userId;
    private String userName;
}
