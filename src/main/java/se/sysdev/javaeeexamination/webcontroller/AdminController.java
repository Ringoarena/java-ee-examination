package se.sysdev.javaeeexamination.webcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping
    public String showAdminIndex(Principal principal, Model model) {
        model.addAttribute("activeuser", principal);
        return "admin/index";
    }

}
