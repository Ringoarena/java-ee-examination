package se.sysdev.javaeeexamination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import se.sysdev.javaeeexamination.model.*;
import se.sysdev.javaeeexamination.repository.*;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class JavaEeExaminationApplication {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    RoleRepository roleRepository;

    public static void main(String[] args) {
        SpringApplication.run(JavaEeExaminationApplication.class, args);
    }

    @Bean
    public CommandLineRunner run() {
        return (args) -> {
            roleRepository.save(new Role("ROLE_USER"));
            Category bikes = categoryRepository.save(new Category("Bikes"));
            Product p1 = productRepository.save(new Product("Pinarello", 50000L, bikes));
            Product p2 = productRepository.save(new Product("S-Works", 70000L, bikes));
            User u1 = userRepository.save(new User("Rikard", "rikardpw", "rikard@gmail.com", null, null));
            List<OrderLine> orderLines = new ArrayList<>();
            orderLines.add(new OrderLine(p1, 2));
            orderLines.add(new OrderLine(p2, 1));
            Order o1 = orderRepository.save(new Order(orderLines, u1, new Address("Stockholm", "Zinkens Väg"), false));
        };
    }

}
