package se.sysdev.javaeeexamination.webcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import se.sysdev.javaeeexamination.dto.UserDto;
import se.sysdev.javaeeexamination.service.UserService;

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
        model.addAttribute("userdto", new UserDto());
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String handleUserRegistration(@ModelAttribute("userdto") UserDto userDto) {
        boolean success = userService.registerUser(userDto);
        if (success) {
            return "redirect:/auth/registration?success";
        }
        return "redirect:/auth/registration?failure";
    }
}