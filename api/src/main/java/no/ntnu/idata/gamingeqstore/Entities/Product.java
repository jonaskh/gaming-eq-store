package no.ntnu.idata.gamingeqstore.Entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product {

    private static int counter = 1; //this static field will increase by one every time an object is made, working as an incremented id.
    @Schema(description = "Unique incrementing product id")
    @Id
    @Column(nullable = false)
    private Integer product_id;

    // Establishing the many-to-many relationship with Category
    @Schema(description = "Which categories the product belong to")
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "product_categories",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<ProductCategory> categories = new HashSet<>();
    @Schema(description = "Name of the product")
    @Column(length = 45, nullable = false, name = "product_name")
    private String productName;

    @Schema(description = "Price of the product")
    @Column(length = 10, nullable = false, name = "price")
    private int price;

    @Schema(description = "Image path of product")
    @Column(length = 1000, nullable = false, name = "product_image")
    private String productImage;

    @Schema(description = "Description of product")
    @Column(length = 1000, nullable = false, name = "product_desc")
    private String productDesc;

    @Schema(description = "How many of this product is in stock")
    @Column(length = 10, nullable = false, name = "productAmount")
    private int productAmount;


    public Product(String productName, int price, String productImage, String productDesc, int productAmount) {
        this.product_id = counter++;
        this.productName = productName;
        this.price = price;
        this.productImage = productImage;
        this.productDesc = productDesc;
        this.productAmount = productAmount;
    }

    public Product() {
        this.product_id = counter++;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer id) {
        this.product_id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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
        return "Product{" +
                "id=" + product_id +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", productAmount=" + productAmount +
                ", productImage='" + productImage + '\'' +
                ", productDesc='" + productDesc + '\'' +
                "categories: " + categories +
                '}';
    }

    public int getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(int productAmount) {
        this.productAmount = productAmount;
    }

    public void addCategory(ProductCategory category) {
        categories.add(category);
    }

    public void printCategories() {
        for (ProductCategory cat : categories) {
            System.out.println(cat);
        }
    }
}
