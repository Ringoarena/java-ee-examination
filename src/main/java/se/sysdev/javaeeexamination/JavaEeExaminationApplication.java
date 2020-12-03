package se.sysdev.javaeeexamination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import se.sysdev.javaeeexamination.model.*;
import se.sysdev.javaeeexamination.repository.*;

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

    public static void main(String[] args) {
        SpringApplication.run(JavaEeExaminationApplication.class, args);
    }

    @Bean
    public CommandLineRunner run() {
        return (args) -> {
//            Category bikes = categoryRepository.save(new Category("Bikes"));
//            Category clothes = categoryRepository.save(new Category("Clothes"));
//            Product p1 = productRepository.save(new Product("Pinarello", 50000L, bikes));
//            Product p2 = productRepository.save(new Product("S-Works", 70000L, bikes));
//            Product p3 = productRepository.save(new Product("Rapha", 1700L, clothes));
        };
    }

}
