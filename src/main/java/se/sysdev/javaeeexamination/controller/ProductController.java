package se.sysdev.javaeeexamination.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
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
    public String showProductIndex(@RequestParam(name = "keyword", required = false, defaultValue = "") String keyword,
                                   @RequestParam(name = "categoryIndex", required = false, defaultValue = "-1") int categoryIndex,
                                   @RequestParam(name = "sortStrategy", required = false, defaultValue = "name") String sortBy,
                                   @RequestParam(name = "ascDesc", required = false, defaultValue = "asc") String ascDesc,
                                   Model model) {
        return showProductsByPage(1, keyword, categoryIndex, sortBy, ascDesc, model);
    }

    @GetMapping("/page/{pageNumber}")
    private String showProductsByPage(@PathVariable("pageNumber") int currentPage,
                                      @RequestParam(name = "keyword", required = false, defaultValue = "") String keyword,
                                      @RequestParam(name = "categoryIndex", required = false, defaultValue = "-1") int categoryIndex,
                                      @RequestParam(name = "sortStrategy", required = false, defaultValue = "name") String sortBy,
                                      @RequestParam(name = "ascDesc", required = false, defaultValue = "asc") String ascDesc,
                                      Model model) {
        List<Category> categoryList = categoryService.findAll();
        Page<Product> page;
        if (categoryIndex == -1) {
            page = productService.findByKeyword(keyword, currentPage, sortBy, ascDesc);
        } else {
            Category selectedCategory = categoryList.get(categoryIndex);
            page = productService.findByCategoryAndKeyword(selectedCategory, keyword, currentPage, sortBy, ascDesc);
        }
        List<Product> products = page.getContent();
        Long totalElements = page.getTotalElements();
        int totalPages = page.getTotalPages();
        model.addAttribute("products", products);
        model.addAttribute("totalElements", totalElements);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("keyword", keyword);
        model.addAttribute("catIndex", categoryIndex);
        model.addAttribute("sortStrategy", sortBy);
        model.addAttribute("ascDesc", ascDesc);
        model.addAttribute("categories", categoryList);
        return "product/index";

    }
}
