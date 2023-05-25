package no.ntnu.idata.gamingeqstore.Entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product {

    private static int counter = 1; //this static field will increase by one every time an object is made, working as an incremented id.
    @Id
    @Column(nullable = false)
    protected Integer product_id;


    // Establishing the many-to-many relationship with Category
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "product_categories",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    protected Set<ProductCategory> categories = new HashSet<>();

    @Column(length = 45, nullable = false, name = "product_name")
    protected String productName;

    @Column(length = 10, nullable = false, name = "price")
    protected int price;

    @Column(length = 1000, nullable = false, name = "product_image")
    protected String productImage;

    @Column(length = 1000, nullable = false, name = "product_desc")
    protected String productDesc;



    public Product(String productName, int price, String productImage, String productDesc) {
        this.product_id = counter++;
        this.productName = productName;
        this.price = price;
        this.productImage = productImage;
        this.productDesc = productDesc;
    }

    public Product() {
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
                ", productImage='" + productImage + '\'' +
                ", productDesc='" + productDesc + '\'' +
                "categories: " + categories +
                '}';
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
