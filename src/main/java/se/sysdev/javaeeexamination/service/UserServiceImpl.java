package se.sysdev.javaeeexamination.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.sysdev.javaeeexamination.dto.UserDto;
import se.sysdev.javaeeexamination.model.User;
import se.sysdev.javaeeexamination.repository.UserRepository;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public Optional<User> createUser(UserDto userDto) {
        userRepository.save(new User(userDto));
        return Optional.empty();
    }
}
