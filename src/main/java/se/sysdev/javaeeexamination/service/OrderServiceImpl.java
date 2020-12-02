package se.sysdev.javaeeexamination.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.sysdev.javaeeexamination.model.Order;
import se.sysdev.javaeeexamination.model.OrderLine;
import se.sysdev.javaeeexamination.repository.OrderRepository;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Override
    public void submitOrder(List<OrderLine> orderLines) {
        orderRepository.save(new Order(orderLines, null, false));
    }
}
