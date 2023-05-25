package no.ntnu.idata.gamingeqstore.Entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class ProductCategory {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany(mappedBy = "categories")
    private Set<Product> products = new HashSet<>();

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
