package se.sysdev.javaeeexamination.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.sysdev.javaeeexamination.model.Category;
import se.sysdev.javaeeexamination.model.Product;

import static org.junit.jupiter.api.Assertions.*;

class CartServiceImplTest {
    private CartServiceImpl cartService;
    private final Category bikes = new Category("bikes");
    private final Product dolan = new Product("dolan", "track", "dolanImg", 100, bikes);

    public CartServiceImplTest() {
        dolan.setId(1L);
    }

    @BeforeEach
    void setUp() {
        cartService = new CartServiceImpl();
    }

    @AfterEach
    void tearDown() {
        cartService = null;
    }

    @Test
    void TestItemCountAndCartTotal() {
        assertEquals(0, cartService.getCartItemCount());
        assertEquals(0, cartService.getCartTotal());
        cartService.addToCart(dolan);
        assertEquals(1, cartService.getCartItemCount());
        assertEquals(100, cartService.getCartTotal());
        cartService.increaseQuantity(1L);
        assertEquals(2, cartService.getCartItemCount());
        assertEquals(200, cartService.getCartTotal());
        cartService.reduceQuantity(1L);
        assertEquals(1, cartService.getCartItemCount());
        assertEquals(100, cartService.getCartTotal());
        cartService.reduceQuantity(1L);
        assertEquals(0, cartService.getCartItemCount());
        assertEquals(0, cartService.getCartTotal());
    }

    @Test
    void TestClearCart() {
        assertEquals(0, cartService.getCartItemCount());
        assertEquals(0, cartService.getCartTotal());
        cartService.addToCart(dolan);
        assertEquals(1, cartService.getCartItemCount());
        assertEquals(100, cartService.getCartTotal());
        cartService.clearCart();
        assertEquals(0, cartService.getCartItemCount());
        assertEquals(0, cartService.getCartTotal());
    }
}