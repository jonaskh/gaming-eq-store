package no.ntnu.idata.gamingeqstore.Entities;



import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, name = "comment_content")
    private String commentContent;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;


    public Comment() {
    }

    public Comment(String commentContent, Product product, User user) {
        this.commentContent = commentContent;
        this.product = this.product;
        this.user = this.user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = this.product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", commentContent='" + commentContent + '\'' +
                ", product=" + product +
                ", user=" + user +
                '}';
    }
}