package se.sysdev.javaeeexamination.service;

import se.sysdev.javaeeexamination.formdata.CategoryFormData;
import se.sysdev.javaeeexamination.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    void createCategory(CategoryFormData categoryFormData);

    Optional<Category> findById(Long categoryId);

    List<Category> findAll();
}
