package se.sysdev.javaeeexamination.service;

import se.sysdev.javaeeexamination.model.Order;
import se.sysdev.javaeeexamination.model.OrderLine;
import se.sysdev.javaeeexamination.model.User;

import java.util.List;

public interface OrderService {
    Order submitOrder(List<OrderLine> orderLines, User user);

}
