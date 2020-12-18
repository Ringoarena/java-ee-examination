package se.sysdev.javaeeexamination.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import se.sysdev.javaeeexamination.formdata.UserFormData;
import se.sysdev.javaeeexamination.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @GetMapping("login")
    public String showUserLoginForm() {
        return "auth/login";
    }

    @GetMapping("/registration")
    public String showUserRegistrationForm(Model model) {
        model.addAttribute("userdto", new UserFormData());
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String handleUserRegistration(@Valid @ModelAttribute("userdto") UserFormData userFormData, Errors errors, Model model) {
        if (!errors.hasErrors()) {
            userService.registerUser(userFormData);
            model.addAttribute("message", "Registration successful!");
            model.addAttribute("userdto", new UserFormData());
        }
        return "auth/registration";
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public String handleEmailAlreadyExists(Model model) {
        model.addAttribute("message", "Email already exists!");
        return "error";
    }
}
