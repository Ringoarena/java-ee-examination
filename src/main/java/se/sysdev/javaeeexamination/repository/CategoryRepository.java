package se.sysdev.javaeeexamination.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.sysdev.javaeeexamination.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
