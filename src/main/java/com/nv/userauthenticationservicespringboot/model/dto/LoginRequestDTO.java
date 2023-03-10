package com.nv.userauthenticationservicespringboot.model.dto;

import lombok.Data;

@Data
public class LoginRequestDTO {
    private String name;
    private String password;
}
