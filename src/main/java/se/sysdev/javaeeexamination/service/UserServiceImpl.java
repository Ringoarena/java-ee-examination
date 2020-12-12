package se.sysdev.javaeeexamination.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import se.sysdev.javaeeexamination.formdata.UserFormData;
import se.sysdev.javaeeexamination.model.Address;
import se.sysdev.javaeeexamination.model.User;
import se.sysdev.javaeeexamination.repository.UserRepository;
import se.sysdev.javaeeexamination.security.CustomUserDetails;
import se.sysdev.javaeeexamination.security.UserRole;

import javax.validation.ConstraintViolationException;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public boolean registerUser(UserFormData userFormData) {
        User user = new User(userFormData.getName()
                , passwordEncoder.encode(userFormData.getPassword())
                , userFormData.getEmail()
                , Arrays.asList(new Address(userFormData.getCity(), userFormData.getStreet()))
                , UserRole.CUSTOMER);
        try {
            userRepository.save(user);
        } catch (ConstraintViolationException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optional = userRepository.findByEmail(email);
        if (!optional.isPresent()) {
            throw new UsernameNotFoundException("User with email " + email + " not found.");
        }
        return new CustomUserDetails(optional.get());
    }
}
