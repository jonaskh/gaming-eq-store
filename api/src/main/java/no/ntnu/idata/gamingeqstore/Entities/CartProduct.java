
package no.ntnu.idata.gamingeqstore.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
    @Entity
    @Table(name = "product_in_cart")
    public class CartProduct {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "product_in_cart_ID")
        private Integer id;

        @ManyToOne(fetch = FetchType.LAZY,
                cascade = CascadeType.REMOVE)
        @JoinColumn(name = "cart_id", referencedColumnName = "id")
        @JsonIgnore
        private Cart cart;

        @Column(nullable = false)
        private String productName;

        @Column(nullable = false, name = "product_id3")
        private int productId;

        @ManyToOne(fetch = FetchType.LAZY)
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


        public CartProduct(Product product) {
            this.productId = product.getProduct_id();
            this.productName = product.getProductName();
            this.price = product.getPrice();
            this.productImage = product.getProductImage();
            this.productDesc = product.getProductDesc();
            this.productAmount = product.getProductAmount();
        }

        public CartProduct() {

        }

        public Integer getId() {
            return id;
        }

        public OrderList getOrderList() {
            return orderList;
        }

        public void setOrderList(OrderList orderList) {
            this.orderList = orderList;
        }

        public Cart getCart() {
            return this.cart;
        }


        public void setCart(Cart cart) {
            this.cart = cart;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public int getProductId() {
            return productId;
        }

        public void setProductId(int productId) {
            this.productId = productId;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getProductImage() {
            return productImage;
        }

        public void setProductImage(String productImage) {
            this.productImage = productImage;
        }

        public String getProductDesc() {
            return productDesc;
        }

        public void setProductDesc(String productDesc) {
            this.productDesc = productDesc;
        }

        public int getProductAmount() {
            return productAmount;
        }

        public void setProductAmount(int productAmount) {
            this.productAmount = productAmount;
        }

        public void incrementAmount() {
            this.productAmount++;
        }

        public void decreaseAmount() {
            this.productAmount--;
        }


        @Override
        public String toString() {
            return "ProductInCart{" +
                    "id=" + id +
                    ", productId='" + productId + '\'' +
                    ", productName='" + productName + '\'' +
                    ", productDescription='" + productDesc + '\'' +
                    ", productIcon='" + productImage + '\'' +
                    ", productPrice=" + price +
                    '}';
        }
    }





