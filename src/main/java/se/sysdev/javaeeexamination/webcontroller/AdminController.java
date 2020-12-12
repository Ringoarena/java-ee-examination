package se.sysdev.javaeeexamination.webcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.sysdev.javaeeexamination.formdata.CategoryDto;
import se.sysdev.javaeeexamination.formdata.ProductDto;
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
        model.addAttribute("categorydto", new CategoryDto());
        model.addAttribute("productform", new ProductFormData());
        return "admin/index";
    }

    @GetMapping("/processed/{orderId}")
    public String altToggleOrderIsProcessed(@PathVariable("orderId") String orderId) {
        orderService.toggleOrderIsProcessed(Long.valueOf(orderId));
        return "redirect:/admin";
    }

    @PostMapping("/category")
    public String submitNewCategory(@ModelAttribute("categorydto") CategoryDto categoryDto) {
        categoryService.createCategory(categoryDto);
        return "redirect:/admin";
    }

    @PostMapping("/product")
    public String submitNewProduct(@ModelAttribute("productdto") ProductFormData form) {
        Optional<Category> optionalCategory = categoryService.findById(form.getCategoryId());
        if (!optionalCategory.isPresent()) {
            return "index";
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
