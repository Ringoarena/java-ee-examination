package se.sysdev.javaeeexamination.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import se.sysdev.javaeeexamination.model.Product;
import se.sysdev.javaeeexamination.service.ProductService;

import java.util.Optional;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    ProductService productService;

    @GetMapping
    public String showCart() {
        return "cart/index";
    }

    @GetMapping("/add/{productId}")
    public String addToCart(@PathVariable("productId") Long productId) {
        Optional<Product> optional = productService.findById(productId);
        if (optional.isPresent()) {
            return "cart/added_to_cart";
        } else {
            return "product/product_not_found";
        }
    }
}
