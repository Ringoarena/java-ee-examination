package se.sysdev.javaeeexamination.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import se.sysdev.javaeeexamination.dto.UserDto;
import se.sysdev.javaeeexamination.model.Address;
import se.sysdev.javaeeexamination.model.Role;
import se.sysdev.javaeeexamination.model.User;
import se.sysdev.javaeeexamination.repository.RoleRepository;
import se.sysdev.javaeeexamination.repository.UserRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Optional<User> registerUser(UserDto userDto) {
        List<Role> roles = new ArrayList<>();
        Optional<Role> optional = roleRepository.findByName("ROLE_USER");
        if (optional.isPresent()) {
            roles.add(optional.get());
        } else {
            roles.add(roleRepository.save(new Role("ROLE_USER")));
        }
        User user = new User(userDto.getName()
                , userDto.getPassword()
                , userDto.getEmail()
                , Arrays.asList(new Address(userDto.getCity(), userDto.getStreet()))
                , roles);
        try {
            userRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
        return Optional.of(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optional = userRepository.findByEmail(email);
        if (!optional.isPresent()) {
            throw new UsernameNotFoundException("User email " + email + " not found.");
        }
        User user = optional.get();
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
    }
}
