
package no.ntnu.idata.gamingeqstore.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;


@Entity
@Table(name = "product_in_order")
public class OrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_in_order_ID")
    private Integer id;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false, name = "product_id2")
    private int productId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private OrderList orderList;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private String productImage;

    @Column(nullable = false)
    private String productDesc;

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

