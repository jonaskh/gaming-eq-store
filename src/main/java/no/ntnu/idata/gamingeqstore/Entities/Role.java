package no.ntnu.idata.gamingeqstore.Entities;

import jakarta.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    private Integer id;

    @Column(nullable = false, length = 50, unique = true)
    private String name;

    public Role() { }

    public Role(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new LinkedHashSet<>();

    public Role(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role(Integer id) {
        this.id = id;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }


    @Override
    public String toString() {
        return this.name;
    }

}
