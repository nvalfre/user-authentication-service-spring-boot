package com.nv.userauthenticationservicespringboot.service.impl;

import com.nv.userauthenticationservicespringboot.config.JwtUtil;
import com.nv.userauthenticationservicespringboot.model.dto.UserResponseDTO;
import com.nv.userauthenticationservicespringboot.model.entity.Phone;
import com.nv.userauthenticationservicespringboot.model.entity.User;
import com.nv.userauthenticationservicespringboot.repository.PhoneRepository;
import com.nv.userauthenticationservicespringboot.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import com.nv.userauthenticationservicespringboot.model.dto.UserDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.nv.userauthenticationservicespringboot.service.UserService;

import javax.persistence.EntityExistsException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    public static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    public static final String PASSWORD_REGEX = "^(?=.*[A-Z])(?=.*\\d{2,})(?=.*[a-z]).{8,12}$";
    private final UserRepository userRepository;
    private final PhoneRepository phoneRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public UserResponseDTO createUser(UserDTO userDto) {
        if (!isValidEmail(userDto.getEmail())) {
            throw new IllegalArgumentException("Invalid email address");
        }
        if (!isValidPassword(userDto.getPassword())) {
            throw new IllegalArgumentException("Invalid password format");
        }
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new EntityExistsException("User with email already exists");
        }

        final User user = buildNewUser(userDto);
        final User save = userRepository.save(user);
        final List<Phone> phones = userDto.getPhones().stream()
                .map(phoneDto -> new Phone(phoneDto.getNumber(), phoneDto.getCitycode(), phoneDto.getContrycode(), user.getId()))
                .collect(Collectors.toList());
        phoneRepository.saveAll(phones);
        final LocalDateTime now = LocalDateTime.now();
        return UserResponseDTO.builder()
                .id(String.valueOf(save.getId()))
                .created(now)
                .lastLogin(now)
                .token(jwtUtil.generateToken(userDto.getName()))
                .isActive(true)
                .build();
    }

    private static User buildNewUser(UserDTO userDto) {
        return new User(userDto.getName(), userDto.getPassword(), userDto.getEmail());
    }

    private boolean isValidEmail(String email) {
        final String regex = EMAIL_REGEX;
        final Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(email).matches();
    }

    private boolean isValidPassword(String password) {
        final String regex = PASSWORD_REGEX;
        final Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(password).matches();
    }

    private String generateToken(UserDTO userDTO) {
        final LocalDateTime expirationTime = LocalDateTime.now().plus(30, ChronoUnit.MINUTES);
        return Jwts.builder()
                .setSubject(userDTO.getName())
                .setExpiration(Date.from(expirationTime.atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS256, "base64EncodedSecretKey")
                .compact();
    }
}
