package org.example.service;

import org.example.authenticate.Authenticator;
import org.example.dao.IUserRepository;
import org.example.dto.CreateUserDto;
import org.example.dto.UserDto;
import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
@Service
public class UserService {
    private IUserRepository userRepository;

    @Autowired
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Collection<UserDto> getUsers() {
        Collection<UserDto> userDtos = new ArrayList<>();
        Collection<User> users = userRepository.getUsers();
        for (User user : users) {
            UserDto userDto = new UserDto(user.getLogin(), user.getRole(), user.getVehicle().getPlate());
            userDtos.add(userDto);
        }
        return userDtos;
    }

    public UserDto getUser(String login) {
        System.out.println("login");
        User user = userRepository.getUser(login);
        if (user != null)
            return new UserDto(user.getLogin(),user.getRole(), user.getVehicle().getPlate());
        else
            return null;
    }

    public String createUser(CreateUserDto createUserDto) {
        User newUser = new User();
        newUser.setLogin(createUserDto.getLogin());
        newUser.setPassword(Authenticator.hashPassword(createUserDto.getPassword()));
        newUser.setRole(User.Role.USER);
        if (newUser.getLogin() == null || newUser.getPassword() == null || newUser.getRole() == null) return null;
        else {
            userRepository.addUser(newUser);
            return "User created";
        }
    }
    public String deleteUser(String login) {
        User user = userRepository.getUser(login);
        if (user == null)
            return "not found";
        else if (user.getVehicle() != null)
            return "vehicle is not null";
        else
            userRepository.removeUser(login);
        return "deleted";
    }

}


