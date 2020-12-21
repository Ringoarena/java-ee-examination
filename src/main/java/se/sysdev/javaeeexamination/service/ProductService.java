package se.sysdev.javaeeexamination.service;

import org.springframework.data.domain.Page;
import se.sysdev.javaeeexamination.dto.ProductDto;
import se.sysdev.javaeeexamination.model.Category;
import se.sysdev.javaeeexamination.model.Product;

import java.util.Optional;

public interface ProductService {

    void createProduct(ProductDto productDto);

    Optional<Product> findById(Long id);

    Page<Product> findByKeyword(String keyword, int pageNumber, String sortBy, String ascDesc);

    Page<Product> findByCategoryAndKeyword(Category category, String keyword, int pageNumber, String sortBy, String ascDesc);

}
