package service;

import model.dto.UserDTO;
import model.entity.User;

import java.util.Optional;

public interface UserService {
    User createUser(UserDTO userDto);

    Optional<User> findByName(String name);

    void update(User user);
}
