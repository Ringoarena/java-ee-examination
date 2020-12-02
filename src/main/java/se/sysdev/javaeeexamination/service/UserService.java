package se.sysdev.javaeeexamination.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import se.sysdev.javaeeexamination.dto.UserDto;
import se.sysdev.javaeeexamination.model.User;

import java.util.Optional;

public interface UserService extends UserDetailsService {
    Optional<User> registerUser(UserDto userDto);
}
