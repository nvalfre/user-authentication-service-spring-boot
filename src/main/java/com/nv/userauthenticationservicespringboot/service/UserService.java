package com.nv.userauthenticationservicespringboot.service;

import com.nv.userauthenticationservicespringboot.model.dto.UserResponseDTO;
import com.nv.userauthenticationservicespringboot.model.entity.User;
import com.nv.userauthenticationservicespringboot.model.dto.UserDTO;

import java.util.Optional;

public interface UserService {
    UserResponseDTO createUser(UserDTO userDto);


}
