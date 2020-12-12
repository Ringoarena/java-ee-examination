package se.sysdev.javaeeexamination.service;

import se.sysdev.javaeeexamination.dto.CategoryDto;
import se.sysdev.javaeeexamination.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    void createCategory(CategoryDto categoryDto);

    Optional<Category> findById(Long categoryId);

    List<Category> findAll();
}
