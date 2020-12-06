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
//
//            productRepository.save(new Product("Cento10NDR", "A bike made for flat stages", "cento10ndr.jpg", 4700L, bikes));
//            productRepository.save(new Product("Cento10SL", "A bike made for mountain stages", "cento10sl.jpg", 2700L, bikes));
//            productRepository.save(new Product("RadioShack", "RadioShack Nissan Trek 2014 jersey", "radioshackjersey.jpg", 150L, clothes));
//
//
//            productRepository.save(new Product("Cento10NDR", "A bike made for flat stages", "cento10ndr.jpg", 4700L, bikes));
//            productRepository.save(new Product("Cento10SL", "A bike made for mountain stages", "cento10sl.jpg", 2700L, bikes));
//            productRepository.save(new Product("RadioShack", "RadioShack Nissan Trek 2014 jersey", "radioshackjersey.jpg", 150L, clothes));
//

        };
    }

}
