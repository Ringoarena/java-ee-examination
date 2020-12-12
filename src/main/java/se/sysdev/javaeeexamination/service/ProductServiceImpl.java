package se.sysdev.javaeeexamination.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.sysdev.javaeeexamination.formdata.ProductDto;
import se.sysdev.javaeeexamination.model.Category;
import se.sysdev.javaeeexamination.model.Product;
import se.sysdev.javaeeexamination.repository.CategoryRepository;
import se.sysdev.javaeeexamination.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void createProduct(ProductDto dto) {
        Product product = new Product(dto.getName(), dto.getDescription(), dto.getImgName(), dto.getPrice(), dto.getCategory());
        productRepository.save(product);
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
