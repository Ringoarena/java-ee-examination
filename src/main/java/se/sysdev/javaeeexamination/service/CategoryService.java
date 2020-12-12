package se.sysdev.javaeeexamination.service;

import se.sysdev.javaeeexamination.dto.CategoryDto;
import se.sysdev.javaeeexamination.model.Category;

import java.util.List;

public interface CategoryService {
    void createCategory(CategoryDto categoryDto);
    List<Category> findAll();
}
