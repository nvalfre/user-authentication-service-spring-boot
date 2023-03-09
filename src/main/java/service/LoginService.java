package service;

import model.dto.UserResponseDTO;

public interface LoginService {
    UserResponseDTO getUserByToken(String token);
}
