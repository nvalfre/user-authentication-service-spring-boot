package com.nv.userauthenticationservicespringboot.service;

import com.nv.userauthenticationservicespringboot.model.dto.UserDTO;
import com.nv.userauthenticationservicespringboot.model.dto.UserResponseDTO;

public interface UserService {
    UserResponseDTO createUser(UserDTO userDto);
}
