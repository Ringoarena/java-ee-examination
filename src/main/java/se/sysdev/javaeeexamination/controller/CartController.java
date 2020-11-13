package se.sysdev.javaeeexamination.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import se.sysdev.javaeeexamination.model.Product;
import se.sysdev.javaeeexamination.service.CartService;
import se.sysdev.javaeeexamination.service.ProductService;

import java.util.Optional;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    ProductService productService;
    @Autowired
    CartService cartService;

    @GetMapping
    public String showCart(Model model) {
        model.addAttribute("cart", cartService.getReadOnlyCart());
        return "cart/index";
    }

    @GetMapping("/add/{productId}")
    public String addToCart(@PathVariable("productId") Long productId, Model model) {
        Optional<Product> optional = productService.findById(productId);
        if (optional.isPresent()) {
            cartService.addProductToCart(optional.get());
            model.addAttribute("addedProduct", optional.get());
            return "cart/added_to_cart";
        } else {
            return "product/product_not_found";
        }
    }

    @GetMapping("/reduce/{productId}")
    public String reduceItemQuantity(@PathVariable("productId") Long productId, Model model) {
        cartService.reduceQuantity(productId);
        model.addAttribute("cart", cartService.getReadOnlyCart());
        return "cart/index";
    }
}
