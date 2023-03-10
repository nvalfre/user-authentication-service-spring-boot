package com.nv.userauthenticationservicespringboot.controller;

import com.nv.userauthenticationservicespringboot.model.dto.ErrorMessage;
import com.nv.userauthenticationservicespringboot.model.dto.UserDTO;
import com.nv.userauthenticationservicespringboot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody UserDTO userDto) {
        try {
            return ResponseEntity.ok(userService.createUser(userDto));
        } catch (Exception e) {
            final HttpStatus badRequest = HttpStatus.BAD_REQUEST;
            return ResponseEntity.status(badRequest).body(ErrorMessage.builder()
                    .message(e.getMessage())
                    .status(badRequest.value())
                    .build());
        }
    }
}
