package com.nv.userauthenticationservicespringboot.service.impl;

import com.nv.userauthenticationservicespringboot.config.JwtUtil;
import com.nv.userauthenticationservicespringboot.model.dto.LoginRequestDTO;
import com.nv.userauthenticationservicespringboot.model.dto.LoginResponseDTO;
import com.nv.userauthenticationservicespringboot.model.dto.UserResponseDTO;
import com.nv.userauthenticationservicespringboot.model.entity.User;
import com.nv.userauthenticationservicespringboot.repository.UserRepository;
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

import java.util.Optional;

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
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private LoginServiceImpl loginService;


    @Test
    public void login() throws Exception {
        LoginRequestDTO loginRequestDTO = LoginRequestDTO.builder().name("name").build();
        String token = "token";
        Optional<User> user = Optional.of(new User());

        when(authenticationManager.authenticate(any())).thenReturn(authentication);
        when(jwtUtil.generateToken(any())).thenReturn(token);
        when(userRepository.findByUserName(any())).thenReturn(user);

        LoginResponseDTO login = loginService.login(loginRequestDTO);

        assertNotNull(login);
        assertEquals(token, login.getToken());
    }
}