
package no.ntnu.idata.gamingeqstore.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Schema(description = "When a cart is checked out, the contents is cleared and copied into an active order, shown in this class")
@Entity
@Table(name = "orderlist")
public class OrderList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer orderId;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    // Relation to order item
    @Column(name = "order_items_id")
    @OneToMany(mappedBy = "orderList", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<OrderProduct> orderItems = new LinkedHashSet<>();

    @Column(nullable = true, name = "total_order_price")
    private BigDecimal totalOrderPrice;

    @Column(nullable = false, name = "order_status")
    private OrderStatus orderStatus;






    public OrderList() {

    }

    public void addOrderItem(OrderProduct orderProduct) {
        orderItems.add(orderProduct);
    }


    //Calculates the sum total of the products added to the order.

    public void setTotalOrderPrice() {
        this.totalOrderPrice = user.getCart().getProducts().stream().map(item ->
                new BigDecimal(item.getPrice()).multiply(new BigDecimal(item.getProductAmount())))
                .reduce(BigDecimal::add).orElse(new BigDecimal((0)));
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
}

