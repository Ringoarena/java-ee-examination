package se.sysdev.javaeeexamination.service;

import se.sysdev.javaeeexamination.model.Order;
import se.sysdev.javaeeexamination.model.OrderLine;
import se.sysdev.javaeeexamination.model.User;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    void submitOrder(List<OrderLine> orderLines, User user);


    List<Order> getOrders();

    void toggleOrderIsProcessed(Long orderId);

    Optional<Order> getSubmittedOrder();
}
