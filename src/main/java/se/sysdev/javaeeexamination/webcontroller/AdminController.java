package se.sysdev.javaeeexamination.webcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.sysdev.javaeeexamination.service.OrderService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    OrderService orderService;

    @GetMapping
    public String showAdminIndex(Model model) {
        model.addAttribute("orders", orderService.getOrders());
        return "admin/index";
    }

    @GetMapping("/processed/{orderId}")
    public String altToggleOrderIsProcessed(@PathVariable("orderId") String orderId) {
        orderService.toggleOrderIsProcessed(Long.valueOf(orderId));
        return "redirect:/admin";
    }

}
