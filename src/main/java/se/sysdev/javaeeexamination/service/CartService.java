package se.sysdev.javaeeexamination.service;

import se.sysdev.javaeeexamination.model.OrderLine;
import se.sysdev.javaeeexamination.model.Product;

import java.util.List;

public interface CartService {

    void addToCart(Product product);

    void increaseQuantity(Long productId);

    void reduceQuantity(Long productId);

    List<OrderLine> getOrderLines();

    int getCartItemCount();

    double getCartTotal();

    void clearCart();
}
