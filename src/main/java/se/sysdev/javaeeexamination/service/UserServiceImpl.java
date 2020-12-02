package se.sysdev.javaeeexamination.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.sysdev.javaeeexamination.dto.UserDto;
import se.sysdev.javaeeexamination.model.Role;
import se.sysdev.javaeeexamination.model.User;
import se.sysdev.javaeeexamination.repository.RoleRepository;
import se.sysdev.javaeeexamination.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Optional<User> createUser(UserDto userDto) {
        List<Role> roles = new ArrayList<>();
        Optional<Role> optional = roleRepository.findByName("ROLE_USER");
        if (optional.isPresent()) {
            System.out.println("Found role_user.");
            roles.add(optional.get());
        } else {
            System.out.println("Couldn't find role_user, creating it...");
            roles.add(roleRepository.save(new Role("ROLE_USER")));

        }
        User user = new User(userDto.getName(), userDto.getPassword(), userDto.getEmail(), userDto.getAddresses(), roles);
        try {
            userRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
        return Optional.of(user);
    }
}
