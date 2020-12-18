package se.sysdev.javaeeexamination.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import se.sysdev.javaeeexamination.formdata.UserFormData;
import se.sysdev.javaeeexamination.model.User;

import java.util.Optional;

public interface UserService extends UserDetailsService {
    void registerUser(UserFormData userFormData);

    Optional<User> findByEmail(String email);
}
