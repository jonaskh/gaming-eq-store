package no.ntnu.idata.gamingeqstore.Controllers;

import no.ntnu.idata.gamingeqstore.Entities.Cart;
import no.ntnu.idata.gamingeqstore.Entities.CartProduct;
import no.ntnu.idata.gamingeqstore.Entities.Product;
import no.ntnu.idata.gamingeqstore.Entities.User;
import no.ntnu.idata.gamingeqstore.Repositories.CartProductRepository;
import no.ntnu.idata.gamingeqstore.Repositories.CartRepository;
import no.ntnu.idata.gamingeqstore.Services.ProductService;
import no.ntnu.idata.gamingeqstore.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private CartProductRepository cartProductRepository;



    @GetMapping("/users/cart/{email}")
    public List<CartProduct> getCartItemsByUserEmail(@PathVariable("email") String email) {

        List<CartProduct> cart = userService.findCartItemsByEmail(email);
        return cart;
    }

    @GetMapping("/save/cart/{email}")
    public void saveCartToUser(@PathVariable("email") String email) {
        Optional<User> user = userService.findByEmail(email);
        if (!user.isPresent()) {
            System.err.println("No user by that email");
        }
        Cart cart = new Cart(user.get());
        cartRepository.save(cart);
    }

    @GetMapping("/users/cart/{email}/{productId}")
    public void saveCartItem(@PathVariable("email") String email, @PathVariable("productId") Integer productId) {
        Optional<User> user = userService.findByEmail(email);
        if (!user.isPresent()) {
            System.err.println("No user by that email");
        }

        Optional<Cart> cart = cartRepository.findById(user.get().getCartID());
        if (!cart.isPresent()) {
            System.err.println("No cart on that user");
        }

        Product product = productService.get(productId);
        CartProduct cartProduct = new CartProduct(product, cart.get());
        userService.saveCartProduct(cartProduct);
    }

    @GetMapping("/cart/amount/{productInCart}/{productAmount}")
    public void updateCartItemAmount(@PathVariable("productInCart") Integer id, @PathVariable("productAmount") Integer productAmount) {
        CartProduct cartProduct = cartProductRepository.findById(id).get();
        cartProduct.setProductAmount(productAmount);
        userService.saveCartProduct(cartProduct);

    }

    @GetMapping("/users/cart/cost/{email}")
    public double getTotalCost(@PathVariable("email") String email) {
        double totalCost = 0;
        Optional<User> user = userService.findByEmail(email);
        if (!user.isPresent()) {
            System.err.println("No user by that email");
        }

        List<CartProduct> items = cartProductRepository.findByCartId(user.get().getCartID());
        for (CartProduct item : items) {
            totalCost += (item.getPrice() * item.getProductAmount());
        }

        return totalCost;
    }

    @DeleteMapping("/delete/cart/item/{itemId}")
    public void deleteCartItem(@PathVariable("itemId") Integer itemId) {
        userService.deleteCartProduct(itemId);
    }

    @PutMapping("/order/create/{email}")
    public void saveOrderFromCart(@PathVariable("email") String email) {
        Optional<User> user = userService.findByEmail(email);

        if (user.isPresent()) {
            Cart cart =  user.get().getCart();
            userService.saveOrderFromCart(cart);
        }


    }
}
