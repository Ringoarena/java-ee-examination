package se.sysdev.javaeeexamination.service;

import se.sysdev.javaeeexamination.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
}
