package se.sysdev.javaeeexamination.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.sysdev.javaeeexamination.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
