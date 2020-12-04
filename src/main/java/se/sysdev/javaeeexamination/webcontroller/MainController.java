package se.sysdev.javaeeexamination.webcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import se.sysdev.javaeeexamination.dto.UserDto;

@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping
    public String showMainIndex() {
        return "index";
    }

    @GetMapping("/login")
    public String showUserLoginForm() {
        return "login";
    }

    @GetMapping("/registration")
    public String showUserRegistrationForm(Model model) {
        model.addAttribute("userdto", new UserDto());
        return "registration";
    }
}
