package se.sysdev.javaeeexamination.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.sysdev.javaeeexamination.controller.dto.ProductDto;
import se.sysdev.javaeeexamination.model.Product;
import se.sysdev.javaeeexamination.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void createProduct(ProductDto dto) {
        productRepository.save(new Product(dto.getName(), dto.getPrice()));
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }
}
