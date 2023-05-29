
package no.ntnu.idata.gamingeqstore.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Schema(description = "When a cart is checked out, the contents is cleared and copied into an active order, shown in this class")
@Entity
@Table(name = "orderlist")
public class OrderList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private Integer orderId;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    // Relation to order item
    @Column(name = "order_items_id")
    @OneToMany(mappedBy = "orderList", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<OrderProduct> orderItems = new LinkedHashSet<>();

    @Column(name = "total_order_price")
    private double totalOrderPrice;

    @Column(nullable = true, name = "order_status")
    private OrderStatus orderStatus;

    @Column(nullable = false, name = "order_date")
    private String orderDate;

    public OrderList(Cart cart) {
        double cost = 0.0;
        this.user = cart.getUser();
        for (CartProduct cartProduct: cart.getProducts()) {
            cost += (cartProduct.getPrice() * cartProduct.getProductAmount());
            OrderProduct orderProduct = new OrderProduct(this, cartProduct);
            orderItems.add(orderProduct);
        }
        this.totalOrderPrice = cost;
        orderDate = LocalDate.now().toString();
    }



    public OrderList() {

    }

    public void addOrderItem(OrderProduct orderProduct) {
        orderItems.add(orderProduct);
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }



    //Order is set to sent by default when created, but admin can go in and cancel the order.


    public enum OrderStatus {

        SENT,

        CANCELED
    }


    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Set<OrderProduct> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderProduct> orderItems) {
        this.orderItems = orderItems;
    }

    public double getTotalOrderPrice() {
        return totalOrderPrice;
    }

    public void setTotalOrderPrice(double totalOrderPrice) {
        this.totalOrderPrice = totalOrderPrice;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
}

