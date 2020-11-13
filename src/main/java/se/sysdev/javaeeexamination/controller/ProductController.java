package se.sysdev.javaeeexamination.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import se.sysdev.javaeeexamination.controller.dto.ProductDto;
import se.sysdev.javaeeexamination.model.Product;
import se.sysdev.javaeeexamination.service.ProductService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String productList(Model model) {
        model.addAttribute("products", productService.findAll());
        return "product/index";
    }

    @GetMapping("/populate")
    public String createProducts(Model model) {
        productService.createProduct(new ProductDto("Pinarello", 88000L));
        productService.createProduct(new ProductDto("S-Works", 60000L));
        productService.createProduct(new ProductDto("Villiers Triestina", 65000L));
        model.addAttribute("products", productService.findAll());
        return "product/index";
    }
}
