package com.nv.userauthenticationservicespringboot.service;

import com.nv.userauthenticationservicespringboot.model.dto.UserResponseDTO;
import com.nv.userauthenticationservicespringboot.model.dto.LoginRequestDTO;

public interface LoginService {
    UserResponseDTO login(LoginRequestDTO loginRequestDTO) throws Exception;
}
