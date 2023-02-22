package no.ntnu.idata.gamingeqstore.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;


@Entity
@Table(name = "product_in_order")
public class ProductInOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_in_order_ID")
    private Integer id;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private String productCategory;

    @Column(nullable = false, name = "product_id")
    private int productId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private OrderList orderList;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private String size;

    @Column(nullable = false)
    private String productImage;

    @Column(nullable = false)
    private String productDesc;

    @Column(nullable = false, name = "quantity")
    private int productAmount;

    /*
    --------------------------------------CONSTRUCTORS-------------------------------------------------------------------
     */

    public ProductInOrder(ProductInCart product) {
        this.productId = product.get
        this.productName = product.getProductName();
        this.productCategory = product.getProductCategory();
        this.price = product.getPrice();
        this.size = product.getSize();
        this.productImage = product.getProductImage();
        this.productDesc = product.getProductDesc();
        this.productAmount = product.getProductAmount();
    }

    public ProductInOrder() {

    }

    public OrderList getOrderList() {
        return orderList;
    }

    public void setOrderList(OrderList orderList) {
        this.orderList = orderList;
    }
}