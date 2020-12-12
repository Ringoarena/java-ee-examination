package se.sysdev.javaeeexamination.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.sysdev.javaeeexamination.formdata.CategoryDto;
import se.sysdev.javaeeexamination.model.Category;
import se.sysdev.javaeeexamination.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void createCategory(CategoryDto categoryDto) {
        Category category = new Category(categoryDto.getName());
        categoryRepository.save(category);
    }

    @Override
    public Optional<Category> findById(Long categoryId) {
        return categoryRepository.findById(categoryId);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
