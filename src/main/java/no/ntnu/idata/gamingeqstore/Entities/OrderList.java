/*
package no.ntnu.idata.gamingeqstore.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;



import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

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
    private Set<ProductInOrder> orderItems = new LinkedHashSet<>();

    @Column(nullable = true, name = "total_order_price")
    private BigDecimal totalOrderPrice;

    @Column(nullable = false, name = "order_status")
    private OrderStatus orderStatus;





/*    public OrderList() {

        this.orderStatus = OrderStatus.SENT;
        createTime = LocalDateTime.now();
    }

    public OrderList() {

    }

    public void addOrderItem(ProductInOrder productInOrder) {
        orderItems.add(productInOrder);
    }

    /*
    Calculates the sum total of the products added to the order.

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


    /*
    Order is set to sent by default when created, but admin can go in and cancel the order.


    public enum OrderStatus {

        SENT,

        CANCELED
    }
}

 */