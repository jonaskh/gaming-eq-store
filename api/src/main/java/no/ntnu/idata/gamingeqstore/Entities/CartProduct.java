
package no.ntnu.idata.gamingeqstore.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
    @Entity
    @Table(name = "product_in_cart")
    public class CartProduct extends Product{
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @ManyToOne(fetch = FetchType.LAZY,
                cascade = CascadeType.REMOVE)
        @JoinColumn(name = "cart_id", referencedColumnName = "id")
        @JsonIgnore
        private Cart cart;


        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "order_id")
        @JsonIgnore
        private OrderList orderList;

        @Column(nullable = false, name = "quantity")
        private int productAmount;


        public CartProduct(Product product) {

            super.setProduct_id(product.product_id);
            super.setProductDesc(product.productDesc);
            super.setPrice(product.price);
            super.setProductImage(productImage);
            super.setProductName(productName);
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
            return "CartProduct{" +
                    "id=" + id +
                    ", productId='" + super.getProduct_id() + '\'' +
                    ", productName='" + super.getProductName() + '\'' +
                    ", productPrice=" + super.getPrice() +
                    '}';
        }
    }





