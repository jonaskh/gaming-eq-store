package no.ntnu.idata.gamingeqstore.Entities;

    import jakarta.persistence.*;
    @Entity
    @Table(name = "products")
    public class Product {

        private static int counter = 1; //this static field will increase by one every time an object is made, working as an incremented id.
        @Id
        @Column(nullable = false)
        private Integer id;

        @Column(length = 45, nullable = false, name = "product_name")
        private String productName;

        @Column(length = 45, nullable = false, name = "product_category")
        private String productCategory;

        @Column(length = 10, nullable = false, name = "price")
        private int price;

        @Column(length = 1000, nullable = false, name = "product_image")
        private String productImage;

        @Column(length = 1000, nullable = false, name = "product_desc")
        private String productDesc;

        @Column(length = 10, nullable = false, name = "productAmount")
        private int productAmount;


        public Product(String productName, String productCategory, int price, String productImage, String productDesc, int productAmount) {
            this.id = counter++;
            this.productName = productName;
            this.productCategory = productCategory;
            this.price = price;
            this.productImage = productImage;
            this.productDesc = productDesc;
            this.productAmount = productAmount;
        }

        public Product() {
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
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

//        public ProductInCart toProductInCart(Product product) {
//
//            return new ProductInCart(product);
//        }



        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", productName='" + productName + '\'' +
                    ", productCategory='" + productCategory + '\'' +
                    ", price=" + price +
                    ", productAmount=" + productAmount +
                    ", productImage='" + productImage + '\'' +
                    ", productDesc='" + productDesc + '\'' +
                    '}';
        }

        public int getProductAmount() {
            return productAmount;
        }

        public void setProductAmount(int productAmount) {
            this.productAmount = productAmount;
        }

    }
