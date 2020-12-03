package se.sysdev.javaeeexamination.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.sysdev.javaeeexamination.model.Order;
import se.sysdev.javaeeexamination.model.OrderLine;
import se.sysdev.javaeeexamination.model.User;
import se.sysdev.javaeeexamination.repository.OrderRepository;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void submitOrder(List<OrderLine> orderLines, User user) {
        orderRepository.save(new Order(orderLines, user, user.getAddresses().get(0), false));
    }
}
