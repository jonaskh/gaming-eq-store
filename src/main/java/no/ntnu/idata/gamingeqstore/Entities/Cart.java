/*
package no.ntnu.idata.gamingeqstore.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    private Integer cartID;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "cart")
    private User user;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER, mappedBy = "cart")
    @Column(name = "product_id_in_cart")
    @JsonIgnore
    private Set<ProductInCart> productsInCart = new HashSet<>();


    public Cart(User user) {
        this.user = user;
    }

    public Cart() {
    }

    public Cart(int cartID, User user, Set<ProductInCart> productsInCart) {
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
    public Set<ProductInCart> getProducts() {
        return productsInCart;
    }

    public void addProductToCart(ProductInCart productInCart) {
        this.productsInCart.add(productInCart);
    }

    public void removeProduct(ProductInCart product) {
        this.productsInCart.remove(product);
    }

}

 */
