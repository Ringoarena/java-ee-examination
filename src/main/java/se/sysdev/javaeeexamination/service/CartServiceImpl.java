package se.sysdev.javaeeexamination.service;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import se.sysdev.javaeeexamination.model.CartItem;
import se.sysdev.javaeeexamination.model.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@SessionScope
public class CartServiceImpl implements CartService {
    List<CartItem> cart = new ArrayList<>();

    @Override
    public void addProductToCart(Product product) {
        if (cartContainsProduct(product)) {
            incrementQuantity(product);
        } else {
            addToCart(product);
        }
    }

    @Override
    public List<CartItem> getReadOnlyCart() {
        return Collections.unmodifiableList(cart);
    }

    @Override
    public void reduceQuantity(Long productId) {
        if (cartContainsProductById(productId)) {
            decrementQuantity(productId);
        }
    }

    private boolean cartContainsProduct(Product product) {
        return cart.stream().anyMatch(cartItem -> cartItem.getProduct().getId().equals(product.getId()));
    }

    private boolean cartContainsProductById(Long productId) {
        return cart.stream().anyMatch(cartItem -> cartItem.getProduct().getId().equals(productId));
    }

    private void decrementQuantity(Long productId) {
        Optional<CartItem> optional = cart.stream()
                .filter(cartItem -> cartItem.getProduct().getId().equals(productId)).findFirst();
        if (optional.isPresent()) {
            CartItem item = optional.get();
            item.setQuantity(item.getQuantity() - 1);
            if (item.getQuantity() == 0) {
                cart.remove(item);
            }
        }
    }

    private void incrementQuantity(Product product) {
        Optional<CartItem> optional = cart.stream()
                .filter(cartItem -> cartItem.getProduct().getId().equals(product.getId())).findFirst();
        if (optional.isPresent()) {
            CartItem item = optional.get();
            item.setQuantity(item.getQuantity() + 1);
        }
    }

    private void addToCart(Product product) {
        cart.add(new CartItem(product, 1));
    }

//    @Override
//    public void addToCart(Product product) {
//        Optional<CartItem> optional = cart.stream()
//                .filter(ci -> ci.getProduct().getId().equals(product.getId()))
//                .findAny();
//        if (optional.isPresent()) {
//            CartItem item = optional.get();
//            item.setQuantity(item.getQuantity() + 1);
//        } else {
//            cart.add(new CartItem(product, 1));
//        }
//    }

}