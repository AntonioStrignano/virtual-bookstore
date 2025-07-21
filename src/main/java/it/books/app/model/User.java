package it.books.app.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    // username
    @NotNull
    @Column(unique = true)
    @NotBlank(message = "Inserisci username.")
    private String username;

    // password
    @NotNull
    @NotBlank(message = "Inserisci password.")
    private String password;

    // second ID
    @Column(name = "second_id", nullable = true, columnDefinition = "INT")
    private Integer secondId;

    // FKs
    // bookstore roles
    // MtO
    @ManyToOne
    @JoinColumn(name = "bookstore_role")
    private BookstoreRole bookstoreRole;

    // security roles
    // MtM
    @ManyToMany(fetch = FetchType.EAGER)
    @NotEmpty(message = "Seleziona almeno un ruolo.")
    @JsonManagedReference
    private List<Role> roles;

// CONSTRUCTORS
    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, List<Role> roles, BookstoreRole bookstoreRole, Integer secondId) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.bookstoreRole = bookstoreRole;
        this.secondId = secondId;
    }

    // ----------------------------
    // ----- GETTERS & SETTERS ----
    // ----------------------------
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Integer getSecondId() {
        return secondId;
    }

    public void setSecondId(Integer secondId) {
        this.secondId = secondId;
    }

    public BookstoreRole getBookstoreRole() {
        return bookstoreRole;
    }

    public void setBookstoreRole(BookstoreRole bookstoreRole) {
        this.bookstoreRole = bookstoreRole;
    }

}
