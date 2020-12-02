package se.sysdev.javaeeexamination.webcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import se.sysdev.javaeeexamination.service.CartService;
import se.sysdev.javaeeexamination.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CartService cartService;

    @GetMapping
    public String showProductIndex(Model model) {
        model.addAttribute("products", productService.findAll());
        model.addAttribute("itemCount", cartService.getCartItemCount());
        return "product/index";
    }
}
