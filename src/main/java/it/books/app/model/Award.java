package it.books.app.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "awards")
public class Award {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    // title
    @NotBlank(message = "Award title missing.")
    @Column(name = "award_title", nullable = false, columnDefinition = "tinytext")
    private String title;

    // year
    @NotNull(message = "Award year missing.")
    @Column(name = "year", nullable = false, columnDefinition = "int(4)")
    private Integer year;

    // description
    @Column(name = "description", columnDefinition = "text(2000)")
    private String description;

    // award type
    @Column(name = "award_type", columnDefinition = "tinytext")
    private String awardType;

    // FKs
    // boooks
    // MtM
    @ManyToMany(mappedBy = "awards")
    private List<Book> books;

    // CONTRUCTORS
    public Award() {
    }

    public Award(String title, Integer year, String description, String awardType) {
        this.title = title;
        this.year = year;
        this.description = description;
        this.awardType = awardType;
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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAwardType() {
        return awardType;
    }

    public void setAwardType(String awardType) {
        this.awardType = awardType;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

}
