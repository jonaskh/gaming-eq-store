package no.ntnu.idata.gamingeqstore.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;


@Schema(description = "One user registered in the database. Has a list of roles defining what he can do on the website")
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Schema(description = "Email/username the user registered with")
    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @Schema(description = "Password used. Will be encrypted when stored.")
    @Column(nullable = false, length = 64)
    private String password;

    @Schema(description = "Zip code")
    @Column(nullable = false, length = 4)
    private Integer zipcode;

    @Schema(description = "Home address")
    @Column(nullable = false, length = 100)
    private String address;

    @Schema(description = "Each user has a list of current orders, created each time a new shopping cart is checked out.")
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<OrderList> orders = new LinkedHashSet<>();

    @Schema(description = "Which roles the user has")
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new LinkedHashSet<>();

    @Schema(description = "Each customer has one cart at all times. Cleared upon checkout.")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private Cart cart = new Cart();

    public User() {
    }

    public User(String email, String password, Integer zipcode, String address) {
        this.email = email;
        this.password = password;
        this.zipcode = zipcode;
        this.address = address;
    }

    public void addOrder(OrderList orderList) {
        orders.add(orderList);
    }

    public Integer getZipcode() {
        return zipcode;
    }

    public void setZipcode(Integer zipcode) {
        this.zipcode = zipcode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role) {
        this.roles.add(role);
        role.getUsers().add(this);
    }

    public Integer getCartID() {
        return cart.getCartID();
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Cart getCart() {
        return cart;
    }
}
