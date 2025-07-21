package it.books.app.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "book_collections")
public class BookCollection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    // title
    @NotBlank(message = "Collection title empty.")
    @Column(name = "coll_title", nullable = false, unique = true, columnDefinition = "tinytext")
    private String title;

    // description
    @Column(name = "coll_description", columnDefinition = "text(2000)")
    private String description;

    // is numerated
    @NotEmpty(message = "Numeration flag missing.")
    @Column(name = "is_numerated", nullable = false, columnDefinition = "bool")
    private Boolean isNumerated;

    // FKs
    // books
    // OtM
    @OneToMany(mappedBy = "bookCollection")
    private List<Book> books;

    // publisher
    // MtO
    @ManyToOne
    @JoinColumn(name = "id_publisher", nullable = false)
    private Publisher publisher;

    // CONSTRUCTORS
    public BookCollection() {
    }

    public BookCollection(String title, String description, Boolean isNumerated, List<Book> books, Publisher publisher) {
        this.title = title;
        this.description = description;
        this.isNumerated = isNumerated;
        this.books = books;
        this.publisher = publisher;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIs_numerated() {
        return isNumerated;
    }

    public void setIs_numerated(Boolean is_numerated) {
        this.isNumerated = is_numerated;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

}
