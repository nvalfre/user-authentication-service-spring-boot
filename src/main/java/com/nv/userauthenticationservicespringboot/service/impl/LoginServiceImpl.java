package com.nv.userauthenticationservicespringboot.service.impl;

import com.nv.userauthenticationservicespringboot.config.JwtUtil;
import com.nv.userauthenticationservicespringboot.model.dto.LoginResponseDTO;
import com.nv.userauthenticationservicespringboot.model.entity.User;
import com.nv.userauthenticationservicespringboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import com.nv.userauthenticationservicespringboot.model.dto.LoginRequestDTO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import com.nv.userauthenticationservicespringboot.service.LoginService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) throws Exception {
        try {
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequestDTO.getName(), loginRequestDTO.getPassword())
            );
            final String token = jwtUtil.generateToken(loginRequestDTO.getName());
            Optional<User> byUserName = userRepository.findByUserName(loginRequestDTO.getName());

            return LoginResponseDTO.builder()
                    .token(token)
                    .name(authenticate.getName())
                    .password(loginRequestDTO.getPassword())
                    .isActive(authenticate.isAuthenticated())
                    .build();
        } catch (Exception ex) {
            throw new Exception("inavalid username/password");
        }
    }
}
