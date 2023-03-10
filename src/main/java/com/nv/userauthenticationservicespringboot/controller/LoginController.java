package com.nv.userauthenticationservicespringboot.controller;

import com.nv.userauthenticationservicespringboot.model.dto.LoginResponseDTO;
import lombok.RequiredArgsConstructor;
import com.nv.userauthenticationservicespringboot.model.dto.LoginRequestDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.nv.userauthenticationservicespringboot.service.LoginService;

@RestController
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO loginRequestDTO) throws Exception {
        return loginService.login(loginRequestDTO);
    }
    @GetMapping("/")
    public String welcome() {
        return "Welcome :)";
    }

}
