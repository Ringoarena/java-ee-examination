package se.sysdev.javaeeexamination.webcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.sysdev.javaeeexamination.dto.CategoryDto;
import se.sysdev.javaeeexamination.service.CategoryService;
import se.sysdev.javaeeexamination.service.OrderService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String showAdminIndex(Model model) {
        model.addAttribute("orders", orderService.getOrders());
        model.addAttribute("categorydto", new CategoryDto());
        return "admin/index";
    }

    @GetMapping("/processed/{orderId}")
    public String altToggleOrderIsProcessed(@PathVariable("orderId") String orderId) {
        orderService.toggleOrderIsProcessed(Long.valueOf(orderId));
        return "redirect:/admin";
    }

    @PostMapping("/category")
    public String handleNewCategory(@ModelAttribute("categorydto") CategoryDto categoryDto) {
        System.out.println(categoryDto.getName());
        categoryService.createCategory(categoryDto);
        return "redirect:/admin";
    }

}
