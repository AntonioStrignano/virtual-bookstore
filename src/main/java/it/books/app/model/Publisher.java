package it.books.app.model;

import java.time.Year;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "publisher")
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "publisher_name", nullable = false, unique = true, columnDefinition = "varchar(50)")
    private String publisherName;

    @Column(name = "founder_year", nullable = false, columnDefinition = "year")
    private Year founderYear;

    @Column(name = "country", nullable = false, columnDefinition = "varchar(30)")
    private String country;

    @Column(name = "city", columnDefinition = "varchar(30)")
    private String city;

    @Column(name = "publisher_website", columnDefinition = "mediumtext")
    private String publisherWebsite;

    @Column(name = "publisher_email", columnDefinition = "varchar(254)")
    private String email;

    @Column(name = "publisher_address", columnDefinition = "tinytext")
    private String publisherAddress;

    @Column(name = "publisher_bio", columnDefinition = "text(2000)")
    private String publisherBio;

    @OneToMany(mappedBy = "publisher")
    private List<Book> books;

    @OneToMany(mappedBy = "publisher")
    private List<BookCollection> bookCollections;

    // ----------------------------
    // ----- GETTERS & SETTERS ----
    // ----------------------------
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public Year getFounderYear() {
        return founderYear;
    }

    public void setFounderYear(Year founderYear) {
        this.founderYear = founderYear;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPublisherWebsite() {
        return publisherWebsite;
    }

    public void setPublisherWebsite(String publisherWebsite) {
        this.publisherWebsite = publisherWebsite;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPublisherAddress() {
        return publisherAddress;
    }

    public void setPublisherAddress(String publisherAddress) {
        this.publisherAddress = publisherAddress;
    }

    public String getPublisherBio() {
        return publisherBio;
    }

    public void setPublisherBio(String publisherBio) {
        this.publisherBio = publisherBio;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<BookCollection> getBookCollections() {
        return bookCollections;
    }

    public void setBookCollections(List<BookCollection> bookCollections) {
        this.bookCollections = bookCollections;
    }

}
