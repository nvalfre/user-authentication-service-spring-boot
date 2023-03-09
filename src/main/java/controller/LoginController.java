package controller;

import lombok.RequiredArgsConstructor;
import model.dto.UserResponseDTO;
import model.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import service.LoginService;
import service.UserService;

@RestController
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @PostMapping("/login")
    public UserResponseDTO login(@RequestHeader("Authorization") String token) {
        User user = userService.findByToken(token);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token");
        }
        user.setToken(JWTUtil.generateToken(user));
        userService.save(user);
        return new UserResponseDTO(user);
    }

}
