package com.nv.userauthenticationservicespringboot.service;

import com.nv.userauthenticationservicespringboot.model.dto.LoginResponseDTO;
import com.nv.userauthenticationservicespringboot.model.dto.LoginRequestDTO;

public interface LoginService {
    LoginResponseDTO login(LoginRequestDTO loginRequestDTO) throws Exception;
}
