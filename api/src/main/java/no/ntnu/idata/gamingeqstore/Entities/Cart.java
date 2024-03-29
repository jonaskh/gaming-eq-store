
package no.ntnu.idata.gamingeqstore.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

/**
 * One cart represents one customers cart and the products within.
 * When cart is checked out the cart is cleared out and the products are moved to an order
 */
@Entity
public class Cart {
    @Schema(description = "Unique incrementing cart id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    private Integer cartID;

    @Schema(description = "Which user the cart belongs to")
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "cart")
    private User user;

    @Schema(description = "List of products in the cart")
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER, mappedBy = "cart")
    @Column(name = "product_id_in_cart")
    @JsonIgnore
    private Set<CartProduct> productsInCart = new HashSet<>();


    public Cart(User user) {
        this.user = user;
    }

    public Cart() {
    }

    public Cart(int cartID, User user, Set<CartProduct> productsInCart) {
        this.cartID = cartID;
        this.user = user;
        this.productsInCart = productsInCart;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getCartID() {
        return cartID;
    }


    @JsonIgnore
    public Set<CartProduct> getProducts() {
        return productsInCart;
    }

    public void addProductToCart(CartProduct cartProduct) {
        this.productsInCart.add(cartProduct);
    }

    public void removeProduct(CartProduct product) {
        this.productsInCart.remove(product);
    }

}


