package se.sysdev.javaeeexamination.webcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import se.sysdev.javaeeexamination.model.OrderLine;
import se.sysdev.javaeeexamination.model.User;
import se.sysdev.javaeeexamination.service.CartService;
import se.sysdev.javaeeexamination.service.OrderService;
import se.sysdev.javaeeexamination.service.UserService;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    CartService cartService;
    @Autowired
    UserService userService;

    @PostMapping("/submit")
    public String handleSubmitOrder(Principal principal) {
        Optional<User> optional = userService.findByEmail(principal.getName());
        if (optional.isPresent()) {
            List<OrderLine> orderLines = cartService.getOrderLines();
            User user = optional.get();
            orderService.submitOrder(orderLines, user);
            System.out.println("User found in handleSubmitOrder");
        } else {
            System.out.println("User not found in handleSubmitOrder");
        }
        return "index";
    }
}
