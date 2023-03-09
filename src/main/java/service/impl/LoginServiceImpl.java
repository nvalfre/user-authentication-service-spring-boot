package service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import model.dto.UserResponseDTO;
import model.entity.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import service.LoginService;
import service.UserService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
    private final UserService userService;

    @Override
    public UserResponseDTO getUserByToken(String token) {
        String username = getUsernameFromToken(token);
        Optional<User> optionalUser = userService.findByName(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            userService.update(user);
            return new UserResponseDTO(user.getId(), user.getCreatedAt(), user.getLastLogin(), user.getToken(), user.isEnabled());
        } else {
            throw new UsernameNotFoundException("User not found with email: " + username);
        }
    }

    private String getUsernameFromToken(String token) {
        final String base64EncodedSecretKey = "";
        Claims claims = Jwts.parser().setSigningKey(base64EncodedSecretKey).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}
