package service;

import model.dto.UserDTO;
import model.entity.User;

public interface UserService {
    User createUser(UserDTO userDto);
}
