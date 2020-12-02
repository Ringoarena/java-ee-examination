package se.sysdev.javaeeexamination.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.sysdev.javaeeexamination.model.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
