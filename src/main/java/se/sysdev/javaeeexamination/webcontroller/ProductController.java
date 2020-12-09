package se.sysdev.javaeeexamination.webcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.sysdev.javaeeexamination.model.Category;
import se.sysdev.javaeeexamination.service.CategoryService;
import se.sysdev.javaeeexamination.service.ProductService;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String showProductIndex(
            Model model
            , @RequestParam(name = "keyword", required = false, defaultValue = "") String keyword
            , @RequestParam(name = "categoryIndex", required = false, defaultValue = "-1") int categoryIndex) {

        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("keyword", keyword);
        model.addAttribute("catIndex", categoryIndex);
        model.addAttribute("categories", categoryList);
        if (categoryIndex == -1) {
            model.addAttribute("products", productService.findByKeyword(keyword));
        } else {
            Category selectedCategory = categoryList.get(categoryIndex);
            model.addAttribute("products", productService.findByCategoryAndKeyword(selectedCategory, keyword));
        }
        return "product/index";
    }
}
