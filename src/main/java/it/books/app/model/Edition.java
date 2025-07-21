package it.books.app.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "edition")
public class Edition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    // name
    @NotBlank(message = "Edition name empty.")
    @Column(name = "edition_name", nullable = false, unique = true, columnDefinition = "varchar(20)")
    private String name;

    // FKs
    // books
    // OtM
    @OneToMany(mappedBy = "edition")
    private List<Book> books;

//CONSTRUCTORS
    public Edition() {
    }

    public Edition(String name) {
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

}
