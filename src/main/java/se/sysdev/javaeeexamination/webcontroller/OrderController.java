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

import javax.swing.text.html.Option;
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
    public String showOrderConfirmation(@ModelAttribute("neworder") Order order, Model model) {
        model.addAttribute("order", order);
        return "order/confirmation";
    }

    @PostMapping("/submit")
    public String handleSubmitOrder(RedirectAttributes redirectAttributes, Principal principal) {
        Optional<User> optionalUser = userService.findByEmail(principal.getName());
        if (optionalUser.isPresent()) {
            List<OrderLine> orderLines = cartService.getOrderLines();
            User user = optionalUser.get();
            Order newOrder = orderService.submitOrder(orderLines, user);
            redirectAttributes.addFlashAttribute("neworder", newOrder);
            cartService.clearCart();
            return "redirect:/order/confirmation";
        }
        return "index";
    }
}
