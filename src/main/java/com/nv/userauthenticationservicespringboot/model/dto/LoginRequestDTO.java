package com.nv.userauthenticationservicespringboot.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginRequestDTO {
    private String name;
    private String password;
}
