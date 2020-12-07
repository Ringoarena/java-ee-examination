package se.sysdev.javaeeexamination.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import se.sysdev.javaeeexamination.model.Order;
import se.sysdev.javaeeexamination.model.OrderLine;
import se.sysdev.javaeeexamination.model.User;
import se.sysdev.javaeeexamination.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
@SessionScope
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    private Optional<Order> submittedOrder = Optional.empty();

    @Override
    public void submitOrder(List<OrderLine> orderLines, User user) {
        Order newOrder = orderRepository.save(new Order(orderLines, user, user.getAddresses().get(0), false));
        submittedOrder = Optional.of(newOrder);
    }

    @Override
    public Optional<Order> getSubmittedOrder() {
        return submittedOrder;
    }
}
