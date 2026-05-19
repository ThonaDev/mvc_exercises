package mvc_exercise.model.service;

import mvc_exercise.model.mapper.UserMapper;
import mvc_exercise.model.User;
import mvc_exercise.model.UserDao;
import mvc_exercise.model.dto.CreateUserDto;
import mvc_exercise.model.dto.UpdateRequestDto;
import mvc_exercise.model.dto.UserResponseDto;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDao userDao = new UserDao();
    private final UserMapper userMapper = new UserMapper();

    // 1. Create User
    @Override
    public UserResponseDto createUser(CreateUserDto createUserDto) {

        User user = userMapper.fromCreateUserDtoToUser(createUserDto);

        userDao.save(user);

        return userMapper.fromUserToResponseDto(user);
    }

    // 6. List all Users
    @Override
    public List<UserResponseDto> getAllUsers() {

        List<User> users = userDao.findAll();

        List<UserResponseDto> userResponseDtos = new ArrayList<>();

        for (User user : users) {

            UserResponseDto userResponseDto =
                    userMapper.fromUserToResponseDto(user);

            userResponseDtos.add(userResponseDto);
        }

        return userResponseDtos;

        // stream api
//        return userDao.findAll()
//                .stream()
//                .map(userMapper::fromUserToResponseDto)
//                .toList();
    }

    // 2. Search User by UUID
    @Override
    public UserResponseDto getUserByUuid(String uuid) {

        User user = userDao.findByUuid(uuid);

        if (user == null) {
            return null;
        }

        return userMapper.fromUserToResponseDto(user);
    }

    // 5. Update User by UUID
    @Override
    public UserResponseDto updateUserByUuid(String uuid, UpdateRequestDto dto) {

        User user = userDao.findByUuid(uuid);

        if (user == null) {
            return null;
        }

        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setPassword(dto.password());
        user.setProfile(dto.profile());

        User updatedUser = userDao.updateByUuid(user);

        if (updatedUser == null) {
            return null;
        }

        return userMapper.fromUserToResponseDto(updatedUser);
    }

    // 4. Delete User by UUID
    @Override
    public int deleteUserByUuid(String uuid) {

        return userDao.removeByUuid(uuid);
    }

    // 3. Search User by Name
    @Override
    public List<UserResponseDto> searchUserByName(String name) {

        List<User> users = userDao.findByName(name);

        List<UserResponseDto> userResponseDtos = new ArrayList<>();

        for (User user : users) {

            UserResponseDto userResponseDto =
                    userMapper.fromUserToResponseDto(user);

            userResponseDtos.add(userResponseDto);
        }

        return userResponseDtos;

        // stream api
//        return userDao.findByName(name)
//                .stream()
//                .map(userMapper::fromUserToResponseDto)
//                .toList();
    }
}