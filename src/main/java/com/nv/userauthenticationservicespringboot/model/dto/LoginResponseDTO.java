package com.nv.userauthenticationservicespringboot.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class LoginResponseDTO {
    private String id;
    private String token;
    private boolean isActive;
    private String name;
    private String password;
}
