package com.nv.userauthenticationservicespringboot.service.impl;

import com.nv.userauthenticationservicespringboot.config.JwtUtil;
import lombok.RequiredArgsConstructor;
import com.nv.userauthenticationservicespringboot.model.dto.LoginRequestDTO;
import com.nv.userauthenticationservicespringboot.model.dto.UserResponseDTO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import com.nv.userauthenticationservicespringboot.service.LoginService;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Override
    public UserResponseDTO login(LoginRequestDTO loginRequestDTO) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequestDTO.getName(), loginRequestDTO.getPassword())
            );
            final String token = jwtUtil.generateToken(loginRequestDTO.getName());
            return UserResponseDTO.builder().token(token).build();
        } catch (Exception ex) {
            throw new Exception("inavalid username/password");
        }
    }
}
