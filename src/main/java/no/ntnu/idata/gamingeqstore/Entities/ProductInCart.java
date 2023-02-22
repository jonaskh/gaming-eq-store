package no.ntnu.idata.gamingeqstore.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
    @Entity
    @Table(name = "product_in_cart")
    public class ProductInCart {
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

        @Column(nullable = false)
        private String productCategory;

        @Column(nullable = false, name = "product_id")
        private int productId;

        @ManyToOne(fetch = FetchType.LAZY)
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

        public ProductInCart(Product product) {
            this.productId = product.getId();
            this.productName = product.getProductName();
            this.productCategory = product.getProductCategory();
            this.price = product.getPrice();
            this.size = product.getSize();
            this.productImage = product.getProductImage();
            this.productDesc = product.getProductDesc();
            this.productAmount = product.getProductAmount();
        }

        public ProductInCart() {

        }

    /*
    --------------------------------------GETTERS AND SETTERS------------------------------------------------------------
     */

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

        public String getProductCategory() {
            return productCategory;
        }

        public void setProductCategory(String productCategory) {
            this.productCategory = productCategory;
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

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
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

    /*
    --------------------------------------METHODS----------------------------------------------------------------------
     */

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
                    ", categoryType=" + productCategory +
                    ", productPrice=" + price +
                    ", size=" + size +
                    '}';
        }
    }
