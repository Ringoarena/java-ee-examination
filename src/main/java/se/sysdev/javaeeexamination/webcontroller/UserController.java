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

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String showUserIndex() {
        return "user/index";
    }

    @GetMapping("/registration")
    public String showUserRegistrationForm(Model model) {
        model.addAttribute("userdto", new UserDto());
        return "user/registration";
    }

    @GetMapping("/details")
    public String showUserDetails(Principal principal, Model model) {
        model.addAttribute("activeuser", principal);
        return "user/details";
    }


    @PostMapping("/registration")
    public String handleUserRegistration(@ModelAttribute("userdto") UserDto userDto) {
        boolean success = userService.registerUser(userDto);
        if (success) {
            return "redirect:/user/registration?success";
        }
        return "redirect:/user/registration?failure";
    }
}
