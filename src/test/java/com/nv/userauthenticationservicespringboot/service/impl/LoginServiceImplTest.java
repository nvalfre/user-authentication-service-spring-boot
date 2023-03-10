package com.nv.userauthenticationservicespringboot.service.impl;

import com.nv.userauthenticationservicespringboot.config.JwtUtil;
import com.nv.userauthenticationservicespringboot.model.dto.LoginRequestDTO;
import com.nv.userauthenticationservicespringboot.model.dto.LoginResponseDTO;
import com.nv.userauthenticationservicespringboot.model.dto.UserResponseDTO;
import com.nv.userauthenticationservicespringboot.service.LoginService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoginServiceImplTest {

    @Mock
    private  AuthenticationManager authenticationManager;
    @Mock
    private  JwtUtil jwtUtil;
    @Mock
    private Authentication authentication;

    @InjectMocks
    private LoginServiceImpl loginService;


    @Test
    public void login() throws Exception {
        LoginRequestDTO loginRequestDTO = LoginRequestDTO.builder().name("name").build();
        when(authenticationManager.authenticate(any())).thenReturn(authentication);
        String token = "token";
        when(jwtUtil.generateToken(any())).thenReturn(token);

        LoginResponseDTO login = loginService.login(loginRequestDTO);

        assertNotNull(login);
        assertEquals(token, login.getToken());
    }
}