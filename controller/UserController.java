package mvc_exercise.controller;

import mvc_exercise.model.dto.CreateUserDto;
import mvc_exercise.model.dto.UpdateRequestDto;
import mvc_exercise.model.dto.UserResponseDto;
import mvc_exercise.model.service.UserService;
import mvc_exercise.model.service.UserServiceImpl;
import mvc_exercise.utils.APIResponseTemplate;

import java.time.LocalDate;
import java.util.List;

public class UserController {

    private final UserService userService = new UserServiceImpl();

    public APIResponseTemplate<UserResponseDto> createUser(CreateUserDto createUserDto) {
        return APIResponseTemplate.<UserResponseDto>builder()
                .status(200)
                .message("Created user successfully! 🎉")
                .timeStamp(LocalDate.now())
                .data(userService.createUser(createUserDto))
                .build();
    }

    public APIResponseTemplate<List<UserResponseDto>> getAllUsers() {
        return new APIResponseTemplate<>(
                200,
                "Get all users successfully",
                LocalDate.now(),
                userService.getAllUsers()
        );
    }

    public APIResponseTemplate<UserResponseDto> getUserByUuid(String uuid) {
        return new APIResponseTemplate<>(
                200,
                "Get user by UUID successfully",
                LocalDate.now(),
                userService.getUserByUuid(uuid)
        );
    }

    public APIResponseTemplate<UserResponseDto> updateUserByUuid(String uuid, UpdateRequestDto dto) {
        return new APIResponseTemplate<>(
                200,
                "Update user successfully",
                LocalDate.now(),
                userService.updateUserByUuid(uuid, dto)
        );
    }

    public APIResponseTemplate<Integer> deleteUserByUuid(String uuid) {
        return new APIResponseTemplate<>(
                200,
                "Delete user successfully",
                LocalDate.now(),
                userService.deleteUserByUuid(uuid)
        );
    }

    public APIResponseTemplate<List<UserResponseDto>> searchUserByName(String name) {
        return new APIResponseTemplate<>(
                200,
                "Search user successfully",
                LocalDate.now(),
                userService.searchUserByName(name)
        );
    }
}