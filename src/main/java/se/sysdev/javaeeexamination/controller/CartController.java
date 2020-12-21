package se.sysdev.javaeeexamination.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import se.sysdev.javaeeexamination.model.OrderLine;
import se.sysdev.javaeeexamination.model.Product;
import se.sysdev.javaeeexamination.service.CartService;
import se.sysdev.javaeeexamination.service.ProductService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    ProductService productService;
    @Autowired
    CartService cartService;

    @GetMapping
    public String showCartIndex(Model model) {
        List<OrderLine> orderLines = cartService.getOrderLines();
        if (orderLines.size() > 0) {
            model.addAttribute("orderlines", orderLines);
            model.addAttribute("carttotal", cartService.getCartTotal());
            return "cart/index";
        } else {
//            model.addAttribute("products", productService.findAll());
            model.addAttribute("message", "Add products before viewing cart!");
            return "error";
        }
    }

    @GetMapping("/add/{productId}")
    public String addToCart(@PathVariable("productId") Long productId, Model model) {
        Optional<Product> optional = productService.findById(productId);
        if (optional.isPresent()) {
            cartService.addToCart(optional.get());
            return "redirect:/products";
        }
        String message = String.format("Product with id %d not found", productId);
        model.addAttribute("message", message);
        return "error";
    }

    @GetMapping("/increase/{productId}")
    public String increaseItemQuantity(@PathVariable("productId") Long productId) {
        cartService.increaseQuantity(productId);
        return "redirect:/cart";
    }

    @GetMapping("/reduce/{productId}")
    public String reduceItemQuantity(@PathVariable("productId") Long productId) {
        cartService.reduceQuantity(productId);
        return "redirect:/cart";
    }
}
