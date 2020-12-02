package se.sysdev.javaeeexamination.service;

import se.sysdev.javaeeexamination.dto.UserDto;
import se.sysdev.javaeeexamination.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> createUser(UserDto userDto);
}
