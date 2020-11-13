package se.sysdev.javaeeexamination.service;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import se.sysdev.javaeeexamination.model.CartItem;
import se.sysdev.javaeeexamination.model.Product;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@SessionScope
public class CartServiceImpl implements CartService {
    List<CartItem> cart = new ArrayList<>();

    @Override
    public void addToCart(Product product) {
        if (cartContains(product)) {
            incrementQuantity(product);
        } else {
            addProduct(product);
        }
        System.out.println("Item added to cart...");
        System.out.println(cart.toString());
    }

    @Override
    public List<CartItem> getReadOnlyCart() {
        return Collections.unmodifiableList(cart);
    }

    private boolean cartContains(Product product) {
        return cart.stream().anyMatch(cartItem -> cartItem.getProduct().getId().equals(product.getId()));
    }

    private void incrementQuantity(Product product) {
        Optional<CartItem> optional = cart.stream().filter(cartItem -> cartItem.getProduct().getId().equals(product.getId())).findFirst();
        if (optional.isPresent()) {
            CartItem item = optional.get();
            item.setQuantity(item.getQuantity() + 1);
        }
    }

    private void addProduct(Product product) {
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
