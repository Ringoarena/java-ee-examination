package se.sysdev.javaeeexamination.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import se.sysdev.javaeeexamination.dto.ProductDto;
import se.sysdev.javaeeexamination.model.Category;
import se.sysdev.javaeeexamination.model.Product;
import se.sysdev.javaeeexamination.repository.ProductRepository;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public void createProduct(ProductDto dto) {
        Product product = new Product(dto.getName(), dto.getDescription(), dto.getImgName(), dto.getPrice(), dto.getCategory());
        productRepository.save(product);
    }

    @Override
    public Page<Product> findAll() {
        Pageable pageable = PageRequest.of(0, 9);
        return productRepository.findAll(pageable);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Page<Product> findByKeyword(String keyword) {
        Pageable pageable = PageRequest.of(0, 9);
        if (keyword != null) {
            return productRepository.findProductsByNameContains(pageable, keyword);
        }
        return productRepository.findAll(pageable);
    }

    @Override
    public Page<Product> findByCategoryAndKeyword(Category category, String keyword) {
        Pageable pageable = PageRequest.of(0, 9);
        return productRepository.findProductsByCategoryEqualsAndNameContains(pageable, category, keyword);
    }
}
