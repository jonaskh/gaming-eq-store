package no.ntnu.idata.gamingeqstore.Services;


import no.ntnu.idata.gamingeqstore.Entities.Cart;
import no.ntnu.idata.gamingeqstore.Entities.CartProduct;
import no.ntnu.idata.gamingeqstore.Entities.OrderList;
import no.ntnu.idata.gamingeqstore.Entities.Role;
import no.ntnu.idata.gamingeqstore.Entities.User;
import no.ntnu.idata.gamingeqstore.Repositories.CartProductRepository;
import no.ntnu.idata.gamingeqstore.Repositories.CartRepository;
import no.ntnu.idata.gamingeqstore.Repositories.OrderListRepository;
import no.ntnu.idata.gamingeqstore.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartProductRepository cartProductRepository;

    @Autowired
    private OrderListRepository orderListRepository;

    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }



// ...

    public User saveUser(User user) {
        if (user.getRoles().isEmpty()) {
            Role defaultRole = roleService.findByName("USER");
            user.addRole(defaultRole);
        }
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    public User saveAdmin(User user) {
        if (user.getRoles().isEmpty()) {
            Role adminRole = roleService.findByName("ADMIN");
            user.addRole(adminRole);
        }
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }


    public List<CartProduct> findCartItemsByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (!user.isPresent()) {
            System.err.println("No user by that email");
            return Collections.emptyList();
        }

//        Optional<Cart> cart = cartRepository.findById(user.get().getCartID());
//        if (!cart.isPresent()) {
//            System.err.println("User has no cart");
//            return Collections.emptyList();
//        }

        return cartProductRepository.findByCartId(user.get().getCartID());
    }

    public void saveCartProduct(CartProduct cartProduct) {
        List<CartProduct> cartProducts = cartProductRepository.findByCartId(cartProduct.getCart().getCartID());
        for (CartProduct product: cartProducts) {
            if (cartProduct.getProductId() == product.getProductId()) {
                product.setProductAmount(product.getProductAmount() + 1);
                cartProductRepository.save(product);
                return;
            }
        }
        cartProductRepository.save(cartProduct);
    }

    public void deleteCartProduct(Integer cartProductId) {
        cartProductRepository.deleteById(cartProductId);
    }

    public void saveOrderFromCart(Cart cart) {
        OrderList orderList = new OrderList(cart);
        orderListRepository.save(orderList);
    }
}
