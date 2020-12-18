package se.sysdev.javaeeexamination.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.sysdev.javaeeexamination.formdata.CategoryFormData;
import se.sysdev.javaeeexamination.dto.ProductDto;
import se.sysdev.javaeeexamination.formdata.ProductFormData;
import se.sysdev.javaeeexamination.model.Category;
import se.sysdev.javaeeexamination.service.CategoryService;
import se.sysdev.javaeeexamination.service.OrderService;
import se.sysdev.javaeeexamination.service.ProductService;

import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;

    @GetMapping
    public String showAdminIndex(Model model) {
        model.addAttribute("orders", orderService.getOrders());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("categorydto", new CategoryFormData());
        model.addAttribute("productform", new ProductFormData());
        return "admin/index";
    }

    @GetMapping("/processed/{orderId}")
    public String altToggleOrderIsProcessed(@PathVariable("orderId") String orderId) {
        orderService.toggleOrderIsProcessed(Long.valueOf(orderId));
        return "redirect:/admin";
    }

    @PostMapping("/category")
    public String submitNewCategory(@ModelAttribute("categorydto") CategoryFormData categoryFormData) {
        categoryService.createCategory(categoryFormData);
        return "redirect:/admin";
    }

    @PostMapping("/product")
    public String submitNewProduct(@ModelAttribute("productdto") ProductFormData form, Model model) {
        Optional<Category> optionalCategory = categoryService.findById(form.getCategoryId());
        if (!optionalCategory.isPresent()) {
            String message = String.format("Category with id %d not found", form.getCategoryId());
            model.addAttribute("message", message);
            return "error";
        }
        ProductDto productDto = new ProductDto(form.getName()
                , form.getDescription()
                , form.getImgName()
                , form.getPrice()
                , optionalCategory.get());
        productService.createProduct(productDto);
        return "redirect:/admin";
    }

}
