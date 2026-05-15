package mvc_exercise.model.service;

import mvc_exercise.model.mapper.UserMapper;
import mvc_exercise.model.User;
import mvc_exercise.model.UserDao;
import mvc_exercise.model.dto.CreateUserDto;
import mvc_exercise.model.dto.UpdateRequestDto;
import mvc_exercise.model.dto.UserResponseDto;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService{
    private final UserDao userDao = new UserDao();
    private final UserMapper userMapper = new UserMapper();

    @Override
    public UserResponseDto createUser(CreateUserDto createUserDto) {
        // create user
        // map from CreateUserDto to User - mapping
        User user = userMapper.fromCreateUserDtoToUser(createUserDto);
        userDao.save(user); // save user to database
        // map from User to UserResponseDto
        UserResponseDto userResponseDto = userMapper.fromUserToResponseDto(user);
        return userResponseDto;
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        // using map to convert object of User to UserResponseDto


         List<User> users = userDao.findAll();

         List<UserResponseDto> userResponseDtos = new ArrayList<>();

         for (User user: users){
         UserResponseDto userResponseDto = userMapper.fromUserToResponseDto(user);


          userResponseDtos.add(userResponseDto);
         }
         return userResponseDtos;



//        return userDao.findAll().stream()
//                .map(userMapper::fromUserToResponseDto).toList();
    }

    @Override
    public UserResponseDto getUserByUuid(String uuid) {
        User user = userDao.findAll()
                .stream()
                .filter(u->u.getUuid().equals(uuid))
                .findFirst().get();
        return userMapper.fromUserToResponseDto(user);
    }

    @Override
    public UserResponseDto updateUserByUuid(String uuid, UpdateRequestDto dto) {

        User user = userDao.findAll()
                .stream()
                .filter(u -> u.getUuid().equals(uuid))
                .findFirst()
                .get();

        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setPassword(dto.password());
        user.setProfile(dto.profile());

        return userMapper.fromUserToResponseDto(user);
    }

    @Override
    public int deleteUserByUuid(String uuid) {
        User user = userDao.findAll()
                .stream()
                .filter(u->u.getUuid().equals(uuid))
                .findFirst().get();
        userDao.remove(user);
        return 1;
    }

    @Override
    public List<UserResponseDto> searchUserByName(String name) {
        List<User> users = userDao.findAll()
                .stream()
                .filter(user -> user.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();

        List<UserResponseDto> userResponseDtos = new ArrayList<>();

        for (User user : users) {
            UserResponseDto userResponseDto = userMapper.fromUserToResponseDto(user);
            userResponseDtos.add(userResponseDto);
        }

        return userResponseDtos;
    }
}
