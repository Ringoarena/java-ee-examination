package se.sysdev.javaeeexamination.webcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import se.sysdev.javaeeexamination.dto.UserDto;
import se.sysdev.javaeeexamination.model.User;
import se.sysdev.javaeeexamination.service.UserService;

import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String showUserIndex() {
        return "user/index";
    }

    @GetMapping("/login")
    public String showUserLoginForm(Model model) {
        return "user/login";
    }

    @GetMapping("/registration")
    public String showUserRegistrationForm(Model model) {
        model.addAttribute("userdto", new UserDto());
        return "user/registration";
    }


    @PostMapping("/registration")
    public String handleUserRegistration(@ModelAttribute("userdto") UserDto userDto) {
        Optional<User> optional = userService.registerUser(userDto);
        if (optional.isPresent()) {
            return "redirect:/user/registration?success";
        }
        return "redirect:/user/registration?failure";
    }
}
