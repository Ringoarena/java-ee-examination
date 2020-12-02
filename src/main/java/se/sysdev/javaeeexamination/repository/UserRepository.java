package se.sysdev.javaeeexamination.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.sysdev.javaeeexamination.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
