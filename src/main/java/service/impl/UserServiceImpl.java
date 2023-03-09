package service.impl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import model.dto.UserDTO;
import model.entity.Phone;
import model.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import repository.UserRepository;
import service.UserService;

import javax.persistence.EntityExistsException;
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
    private final PasswordEncoder passwordEncoder;

    public User createUser(UserDTO userDto) {
        if (!isValidEmail(userDto.getEmail())) {
            throw new IllegalArgumentException("Invalid email address");
        }
        if (!isValidPassword(userDto.getPassword())) {
            throw new IllegalArgumentException("Invalid password format");
        }
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new EntityExistsException("User with email already exists");
        }

        final List<Phone> phones = userDto.getPhones().stream()
                .map(phoneDto -> new Phone(phoneDto.getNumber(), phoneDto.getCityCode(), phoneDto.getCountryCode()))
                .collect(Collectors.toList());
        final String token = generateToken(userDto);
        final String encode = passwordEncoder.encode(userDto.getPassword());

        return userRepository.save(buildNewUser(userDto, phones, token, encode));
    }

    @Override
    public Optional<User> findByName(String token) {
        return userRepository.findByName(token);
    }

    @Override
    public void update(User user) {
        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);
    }

    private static User buildNewUser(UserDTO userDto, List<Phone> phones, String token, String encode) {
        return new User(userDto.getName(), userDto.getEmail(), encode, LocalDateTime.now(), LocalDateTime.now(), token, true, phones);
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
        final String base64EncodedSecretKey = "";
        final LocalDateTime expirationTime = LocalDateTime.now().plus(30, ChronoUnit.MINUTES);
        return Jwts.builder()
                .setSubject(userDTO.getName())
                .setExpiration(Date.from(expirationTime.atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS256, base64EncodedSecretKey)
                .compact();
    }
}
