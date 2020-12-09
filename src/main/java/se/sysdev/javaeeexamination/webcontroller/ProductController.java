package se.sysdev.javaeeexamination.webcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.sysdev.javaeeexamination.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public String showProductIndex(Model model, @RequestParam(name = "keyword", required = false, defaultValue = "") String keyword) {
        model.addAttribute("keyword", keyword);
        model.addAttribute("products", productService.findByKeyword(keyword));
        return "product/index";
    }
}
