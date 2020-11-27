package se.sysdev.javaeeexamination.service;

import se.sysdev.javaeeexamination.dto.ProductDto;
import se.sysdev.javaeeexamination.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    void createProduct(ProductDto dto);

    List<Product> findAll();

    Optional<Product> findById(Long id);

}
