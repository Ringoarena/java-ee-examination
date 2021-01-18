package se.sysdev.javaeeexamination.service;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import se.sysdev.javaeeexamination.model.OrderLine;
import se.sysdev.javaeeexamination.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@SessionScope
public class CartServiceImpl implements CartService {
    private List<OrderLine> orderLines = new ArrayList<>();

    @Override
    public void addToCart(Product product) {
        if (cartContainsProduct(product)) {
            increaseQuantity(product.getId());
        } else {
            doAddToCart(product);
        }
    }

    private boolean cartContainsProduct(Product product) {
        return orderLines.stream().anyMatch(orderLine -> orderLine.getProduct().getId().equals(product.getId()));
    }

    @Override
    public void increaseQuantity(Long productId) {
        Optional<OrderLine> optional = orderLines.stream()
                .filter(orderLine -> orderLine.getProduct().getId().equals(productId)).findFirst();
        if (optional.isPresent()) {
            OrderLine item = optional.get();
            item.setQuantity(item.getQuantity() + 1);
        }
    }

    private void doAddToCart(Product product) {
        orderLines.add(new OrderLine(product, 1));
    }

    @Override
    public void reduceQuantity(Long productId) {
        Optional<OrderLine> optional = orderLines.stream()
                .filter(orderLine -> orderLine.getProduct().getId().equals(productId)).findFirst();
        if (optional.isPresent()) {
            OrderLine item = optional.get();
            item.setQuantity(item.getQuantity() - 1);
            if (item.getQuantity() == 0) {
                orderLines.remove(item);
            }
        }
    }

    @Override
    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    @Override
    public int getCartItemCount() {
        return orderLines.stream().mapToInt(OrderLine::getQuantity).sum();
    }

    @Override
    public double getCartTotal() {
        double cartTotal = 0;
        for (OrderLine orderLine : orderLines) {
            cartTotal += orderLine.getOrderLineTotal();
        }
        return cartTotal;
    }

    @Override
    public void clearCart() {
        orderLines = new ArrayList<>();
    }

}
