package com.nv.userauthenticationservicespringboot.controller;

import lombok.RequiredArgsConstructor;
import com.nv.userauthenticationservicespringboot.model.dto.UserResponseDTO;
import com.nv.userauthenticationservicespringboot.model.dto.UserDTO;
import com.nv.userauthenticationservicespringboot.model.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.nv.userauthenticationservicespringboot.service.UserService;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<UserResponseDTO> signUp(@RequestBody UserDTO userDto) {
        try {
            return ResponseEntity.ok(userService.createUser(userDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
