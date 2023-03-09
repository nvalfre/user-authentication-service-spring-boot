package service.impl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import model.dto.UserDTO;
import model.entity.Phone;
import model.entity.User;
import org.springframework.stereotype.Service;
import repository.UserRepository;
import service.UserService;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    public User createUser(UserDTO userDto) {

        List<Phone> phones = userDto.getPhones().stream()
                .map(phoneDto -> new Phone(phoneDto.getNumber(), phoneDto.getCityCode(), phoneDto.getCountryCode()))
                .collect(Collectors.toList());

        String base64EncodedSecretKey = "";
        String token = Jwts.builder()
                .setSubject(userDto.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(Date.from(LocalDateTime.now().plusMinutes(30L).atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS256, base64EncodedSecretKey)
                .compact();

        User user = new User(userDto.getName(), userDto.getEmail(), userDto.getPassword(), LocalDateTime.now(), LocalDateTime.now(), token, true, phones);
        userRepository.save(user);

        return user;
    }
}
