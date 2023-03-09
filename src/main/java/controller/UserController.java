package controller;

import lombok.RequiredArgsConstructor;
import model.dto.UserResponseDTO;
import model.dto.UserDTO;
import model.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import service.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<UserResponseDTO> signUp(@RequestBody UserDTO userDto) {
        try {
            User user = userService.createUser(userDto);
            UserResponseDTO response = new UserResponseDTO(user.getId(), user.getCreatedAt(),
                    user.getLastLogin(), user.getToken(), user.isEnabled());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
