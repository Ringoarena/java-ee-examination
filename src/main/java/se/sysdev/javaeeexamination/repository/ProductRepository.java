package se.sysdev.javaeeexamination.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import se.sysdev.javaeeexamination.model.Category;
import se.sysdev.javaeeexamination.model.Product;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

    Page<Product> findProductsByNameContains(Pageable pageable, String keyword);

    Page<Product> findProductsByCategoryEqualsAndNameContains(Pageable pageable, Category category, String keyword);

}
