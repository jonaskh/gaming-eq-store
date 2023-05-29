package no.ntnu.idata.gamingeqstore.Entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Schema(description = "Category is set as an entity so it can have a manytomany relationship with products")
@Entity
public class ProductCategory {
    @Id
    @GeneratedValue
    private Long id;

    @Schema(description = "List of products with the given category")
    @ManyToMany(mappedBy = "categories")
    private Set<Product> products = new HashSet<>();

    @Schema(description = "Name of category")
    @Column
    private String categoryName;

    public ProductCategory() {

    }
    public ProductCategory(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String category) {
        this.categoryName = category;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return categoryName;
    }
}
