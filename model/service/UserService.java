package mvc_exercise.model.service;

import mvc_exercise.model.dto.CreateUserDto;
import mvc_exercise.model.dto.UpdateRequestDto;
import mvc_exercise.model.dto.UserResponseDto;

import java.util.List;

public interface UserService {
    UserResponseDto createUser(CreateUserDto createUserDto);
    List<UserResponseDto> getAllUsers();
    UserResponseDto getUserByUuid(String uuid);
    UserResponseDto updateUserByUuid(String uuid, UpdateRequestDto updateRequestDto);
    int deleteUserByUuid(String uuid);
    List<UserResponseDto> searchUserByName(String name);
}
