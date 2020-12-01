package se.sysdev.javaeeexamination.service;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import se.sysdev.javaeeexamination.model.OrderLine;
import se.sysdev.javaeeexamination.model.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@SessionScope
public class CartServiceImpl implements CartService {
    List<OrderLine> cart = new ArrayList<>();

    @Override
    public void addToCart(Product product) {
        if (cartContainsProduct(product)) {
            incrementQuantity(product);
        } else {
            doAddToCart(product);
        }
    }

    @Override
    public List<OrderLine> getReadOnlyCart() {
        return Collections.unmodifiableList(cart);
    }

    @Override
    public void reduceQuantity(Long productId) {
        if (cartContainsProductById(productId)) {
            decrementQuantity(productId);
        }
    }

    @Override
    public int getCartItemCount() {
        return cart.stream().mapToInt(OrderLine::getQuantity).sum();
    }

    private boolean cartContainsProduct(Product product) {
        return cart.stream().anyMatch(orderLine -> orderLine.getProduct().getId().equals(product.getId()));
    }

    private boolean cartContainsProductById(Long productId) {
        return cart.stream().anyMatch(orderLine -> orderLine.getProduct().getId().equals(productId));
    }

    private void decrementQuantity(Long productId) {
        Optional<OrderLine> optional = cart.stream()
                .filter(orderLine -> orderLine.getProduct().getId().equals(productId)).findFirst();
        if (optional.isPresent()) {
            OrderLine item = optional.get();
            item.setQuantity(item.getQuantity() - 1);
            if (item.getQuantity() == 0) {
                cart.remove(item);
            }
        }
    }

    private void incrementQuantity(Product product) {
        Optional<OrderLine> optional = cart.stream()
                .filter(orderLine -> orderLine.getProduct().getId().equals(product.getId())).findFirst();
        if (optional.isPresent()) {
            OrderLine item = optional.get();
            item.setQuantity(item.getQuantity() + 1);
        }
    }

    private void doAddToCart(Product product) {
        cart.add(new OrderLine(product, 1));
    }
}
