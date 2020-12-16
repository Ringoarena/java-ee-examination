package se.sysdev.javaeeexamination.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import se.sysdev.javaeeexamination.service.OrderService;

@Controller
@RequestMapping("/api")
public class OrderResource {
    @Autowired
    private OrderService orderService;

    @GetMapping("/public")
    public String getPublicData() {
        return "Public";
    }

    @GetMapping("/private")
    public String getPrivateData() {
        return "Private";
    }
    
}
