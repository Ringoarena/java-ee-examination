package se.sysdev.javaeeexamination.webcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import se.sysdev.javaeeexamination.model.Order;
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

    @GetMapping
    public String showMainIndex() {
        return "redirect:/";
    }

    @GetMapping("/confirmation")
    public String showOrderConfirmation(Model model) {
        Optional<Order> optionalOrder = orderService.getSubmittedOrder();
        if (optionalOrder.isPresent()) {
            model.addAttribute("order", optionalOrder.get());
            return "order/confirmation";
        }
        return "redirect:/products";
    }

    @PostMapping("/submit")
    public String handleSubmitOrder(Principal principal) {
        Optional<User> optionalUser = userService.findByEmail(principal.getName());
        if (optionalUser.isPresent()) {
            List<OrderLine> orderLines = cartService.getOrderLines();
            User user = optionalUser.get();
            orderService.submitOrder(orderLines, user);
            cartService.clearCart();
            return "redirect:/order/confirmation";
        }
        return "index";
    }
}
