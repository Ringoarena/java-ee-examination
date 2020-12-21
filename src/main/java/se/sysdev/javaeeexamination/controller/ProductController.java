package se.sysdev.javaeeexamination.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.sysdev.javaeeexamination.model.Category;
import se.sysdev.javaeeexamination.model.Product;
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
        return showProductsByPage(1, keyword, categoryIndex, model);
    }

    @GetMapping("/page/{pageNumber}")
    private String showProductsByPage(@PathVariable("pageNumber") int currentPage,
                                      @RequestParam(name = "keyword", required = false, defaultValue = "") String keyword,
                                      @RequestParam(name = "categoryIndex", required = false, defaultValue = "-1") int categoryIndex,
                                      Model model) {
        List<Category> categoryList = categoryService.findAll();
        Page<Product> page;
        List<Product> products;
        if (categoryIndex == -1) {
            page = productService.findByKeyword(keyword, currentPage);
        } else {
            Category selectedCategory = categoryList.get(categoryIndex);
            page = productService.findByCategoryAndKeyword(selectedCategory, keyword, currentPage);
        }
        Long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();
        products = page.getContent();
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("products", products);
        model.addAttribute("keyword", keyword);
        model.addAttribute("catIndex", categoryIndex);
        model.addAttribute("categories", categoryList);
        return "product/index";

    }
}
