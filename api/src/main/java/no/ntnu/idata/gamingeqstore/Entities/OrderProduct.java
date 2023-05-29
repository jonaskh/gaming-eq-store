
package no.ntnu.idata.gamingeqstore.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Schema(description = "This is the same as cart product but for order. One product is copied into cart product and cart product is copied to order product upon checkout.")
@Entity
@Table(name = "product_in_order")
public class OrderProduct {
    @Schema(description = "incrementing uninque id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_in_order_ID")
    private Integer id;

    @Schema(description = "Name of order product")
    @Column(nullable = false)
    private String productName;

    @Schema(description = "")
    @Column(nullable = false, name = "product_id2")
    private int productId;


    @Schema(description = "Which order list the order product is tied to")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private OrderList orderList;

    @Schema(description = "Price of order product")
    @Column(nullable = false)
    private int price;

    @Schema(description = "Path to product image")
    @Column(nullable = false)
    private String productImage;

    @Schema(description = "Description of order product")
    @Column(nullable = false)
    private String productDesc;

    @Schema(description = "How many is in the cart/order of each individual product")
    @Column(nullable = false, name = "quantity")
    private int productAmount;


    public OrderProduct(OrderList order, CartProduct product) {
        this.orderList = order;
        this.productId = product.getId();
        this.productName = product.getProductName();
        this.price = product.getPrice();
        this.productImage = product.getProductImage();
        this.productDesc = product.getProductDesc();
        this.productAmount = product.getProductAmount();
    }

    public OrderProduct() {

    }

    public OrderList getOrderList() {
        return orderList;
    }

    public void setOrderList(OrderList orderList) {
        this.orderList = orderList;
    }
}

