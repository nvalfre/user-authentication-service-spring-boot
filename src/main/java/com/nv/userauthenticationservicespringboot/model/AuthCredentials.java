package com.nv.userauthenticationservicespringboot.model;

import lombok.Data;

@Data
public class AuthCredentials {
    private String email;
    private String password;
}
