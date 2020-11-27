package se.sysdev.javaeeexamination.service;

import se.sysdev.javaeeexamination.model.CartItem;
import se.sysdev.javaeeexamination.model.Product;

import java.util.List;

public interface CartService {

    void addToCart(Product product);

    void reduceQuantity(Long productId);

    List<CartItem> getReadOnlyCart();

    int getCartItemCount();
}
