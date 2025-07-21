package it.books.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "bookstore_roles")
public class BookstoreRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "role_name", nullable = false, unique = true, columnDefinition = ("varchar(30)"))
    private String roleName;

    public BookstoreRole() {
    }

    public BookstoreRole(String roleName) {
        this.roleName = roleName;
    }

// ---------------------------
// --- GETTERS AND SETTERS ---
// ---------------------------
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}
