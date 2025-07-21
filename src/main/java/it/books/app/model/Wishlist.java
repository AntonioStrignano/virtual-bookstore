package it.books.app.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "wishlists")
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    // FKs
    // customer id
    // one to one
    @OneToOne(mappedBy = "wishlistId")
    private Customer customerId;

    // wishlisted books
    // many to many
    @ManyToMany
    @JoinTable(name = "wishlists_books", joinColumns = @JoinColumn(name = "id_whishlist"), inverseJoinColumns = @JoinColumn(name = "id_book"))
    private List<Book> wishlistedBooks;

    // ---- CONSTRUCTORS ----
    public Wishlist() {
    }

    public Wishlist(Customer customer) {
        this.customerId = customer;
    }

    public Wishlist(List<Book> books) {
        this.wishlistedBooks = books;
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

    public Customer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
    }

    public List<Book> getWishlistedBooks() {
        return wishlistedBooks;
    }

    public void setWishlistedBooks(List<Book> wishlistedBooks) {
        this.wishlistedBooks = wishlistedBooks;
    }

}
