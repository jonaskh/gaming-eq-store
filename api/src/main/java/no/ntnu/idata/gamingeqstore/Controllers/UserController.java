package no.ntnu.idata.gamingeqstore.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import no.ntnu.idata.gamingeqstore.Entities.Cart;
import no.ntnu.idata.gamingeqstore.Entities.CartProduct;
import no.ntnu.idata.gamingeqstore.Entities.OrderList;
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

@CrossOrigin(origins = "http://group09.web-tek.ninja")
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

    @GetMapping("/api/users/cart/{email}")
    @Operation(summary = "Return items in cart", description = "Returns current items in the cart belonging to email")
    public List<CartProduct> getCartItemsByUserEmail(@PathVariable("email") String email) {

        List<CartProduct> cart = userService.findCartItemsByEmail(email);
        return cart;
    }

    @GetMapping("/api/users/order/{email}")
    @Operation(summary = "Return all orders to a customer", description = "All customers can have several orders, display these")
    public List<OrderList> getOrdersByUserEmail(@PathVariable("email") String email) {
        return userService.findOrdersByEmail(email);
    }

    @GetMapping("/api/save/cart/{email}")
    @Operation(summary = "Create the cart to a user", description = "Saves one cart to each user")
    public void saveCartToUser(@PathVariable("email") String email) {
        Optional<User> user = userService.findByEmail(email);
        if (!user.isPresent()) {
            System.err.println("No user by that email");
        }
        Cart cart = new Cart(user.get());
        cartRepository.save(cart);
    }

    @GetMapping("/api/users/cart/{email}/{productId}")
    @Operation(summary = "Save one item to the cart", description = "When clicking add to cart on product page save to users cart")
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

    @GetMapping("/api/cart/amount/{productInCart}/{productAmount}")
    @Operation(summary = "Each several objects of one product", description = "Each product can be added multiple times, do this here")
    public void updateCartItemAmount(@PathVariable("productInCart") Integer id, @PathVariable("productAmount") Integer productAmount) {
        CartProduct cartProduct = cartProductRepository.findById(id).get();
        cartProduct.setProductAmount(productAmount);
        userService.saveCartProduct(cartProduct);

    }

    @GetMapping("/api/users/cart/cost/{email}")
    @Operation(summary = "Sum of all product prices in cart", description = "Returns the total sum to all products in each cart")
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

    @DeleteMapping("/api/delete/cart/item/{itemId}")
    @Operation(summary = "Delete one product from cart", description = "Removes one product from cart")
    public void deleteCartItem(@PathVariable("itemId") Integer itemId) {
        userService.deleteCartProduct(itemId);
    }

    @DeleteMapping("/api/delete/cart/all/{email}")
    @Operation(summary = "Delete all products in cart", description = "Deletes all products from the cart, for example upon checkout")
    public void deleteCartAll(@PathVariable("email") String email) {
        Optional<User> user = userService.findByEmail(email);

        if (user.isPresent()) {
            Cart cart =  user.get().getCart();
            userService.deleteCartProducts(cart);
        }
    }

    @PutMapping("/api/order/create/{email}")
    @Operation(summary = "Create an order from a cart", description = "Upon check out, an order with same products as cart is made")
    public void saveOrderFromCart(@PathVariable("email") String email) {
        Optional<User> user = userService.findByEmail(email);

        if (user.isPresent()) {
            Cart cart =  user.get().getCart();
            userService.saveOrderFromCart(cart);
        }
    }


}
