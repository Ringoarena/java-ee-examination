package se.sysdev.javaeeexamination.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import se.sysdev.javaeeexamination.formdata.UserFormData;
import se.sysdev.javaeeexamination.model.Address;
import se.sysdev.javaeeexamination.model.User;
import se.sysdev.javaeeexamination.repository.UserRepository;
import se.sysdev.javaeeexamination.security.UserRole;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public ServiceResponse registerUser(UserFormData userFormData) {
        ServiceResponse response = new ServiceResponse();
        response.setHasErrors(false);
        User user = new User(userFormData.getName()
                , passwordEncoder.encode(userFormData.getPassword())
                , userFormData.getEmail()
                , Arrays.asList(new Address(userFormData.getCity(), userFormData.getStreet()))
                , UserRole.CUSTOMER);
        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            response.setHasErrors(true);
            response.setMessage("Email already exists.");
        }
        return response;
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
        User user = optional.get();
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getUserRole())));
    }
}
