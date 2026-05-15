package mvc_exercise.model.mapper;

import mvc_exercise.model.User;
import mvc_exercise.model.dto.CreateUserDto;
import mvc_exercise.model.dto.UserResponseDto;

import java.util.Random;
import java.util.UUID;

public class UserMapper {
    public User fromCreateUserDtoToUser(CreateUserDto createUserDto){
        return  new User(new Random().nextInt(9999999),
                UUID.randomUUID().toString(),
                createUserDto.name(),createUserDto.email(),
                createUserDto.password(), "https://img.magnific.com/premium-vector/black-man-with-curly-hair-avatar-flat-cartoon-vector_621660-6821.jpg?semt=ais_hybrid&w=740&q=80");
    }

    public UserResponseDto fromUserToResponseDto(User user){
        return new UserResponseDto(user.getUuid(),
                user.getName(),
                user.getEmail(),
                user.getProfile());
    }
}
