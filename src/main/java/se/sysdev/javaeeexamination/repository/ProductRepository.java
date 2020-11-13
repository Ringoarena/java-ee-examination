package se.sysdev.javaeeexamination.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.sysdev.javaeeexamination.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
