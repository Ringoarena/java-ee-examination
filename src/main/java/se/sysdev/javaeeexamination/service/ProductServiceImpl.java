package se.sysdev.javaeeexamination.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.sysdev.javaeeexamination.dto.ProductDto;
import se.sysdev.javaeeexamination.model.Category;
import se.sysdev.javaeeexamination.model.Product;
import se.sysdev.javaeeexamination.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product createProduct(ProductDto dto) {
        return productRepository.save(new Product(dto.getName(), dto.getPrice(), dto.getCategory()));
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> findByKeyword(String keyword) {
        if (keyword != null) {
            return productRepository.findProductsByNameContains(keyword);
        }
        return productRepository.findAll();
    }

    @Override
    public List<Product> findByCategoryAndKeyword(Category category, String keyword) {
        return productRepository.findProductsByCategoryEqualsAndNameContains(category, keyword);
    }
}
