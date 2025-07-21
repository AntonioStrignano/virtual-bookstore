package it.books.app.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    // first name
    @NotBlank(message = "Customer first name missing.")
    @Column(name = "first_name", nullable = false, columnDefinition = "tinytext")
    private String firstName;

    // last name
    @NotBlank(message = "Customer last name missing.")
    @Column(name = "last_name", nullable = false, columnDefinition = "tinytext")
    private String lastName;

    // phone number
    @NotNull(message = "Customer phone number missing.")
    @Column(name = "phone_number", nullable = false, columnDefinition = "varchar(20)", unique = true)
    private String phoneNumber;

    // address
    @NotBlank(message = "Customer address missing.")
    @Column(name = "address", nullable = false, columnDefinition = "tinytext")
    private String address;

    // preferences(json)
    @Column(name = "preferences", nullable = false, columnDefinition = "JSON")
    private String preferences;

    // ----- FKs -----
    // analytics
    // OtM
    @OneToMany(mappedBy = "customerId")
    private List<Analytic> analytics;

    // cart
    // OtO
    @OneToOne
    @JoinColumn(name = "id_cart")
    private Cart cartId;

    // search history
    // OtM
    @OneToMany(mappedBy = "customerId")
    private List<SearchHistory> searchHistory;

    // wishlist
    // OtO
    @OneToOne
    @JoinColumn(name = "id_wishlist")
    private Wishlist wishlistId;

    // order
    // OtM
    @OneToMany(mappedBy = "customerId")
    private List<Order> orders;

    // notifications
    // OtM
    @OneToMany(mappedBy = "customerId")
    private List<Notification> notifications;

    // review
    // OtM
    @OneToMany(mappedBy = "customerId")
    private List<Review> reviews;

    // CONSTRUCTORS
    public Customer() {
    }

    public Customer(String firstName, String lastName, String phone, String address, String preferences, Cart cart, Wishlist wishlist) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phone;
        this.address = address;
        this.preferences = preferences;
        this.cartId = cart;
        this.wishlistId = wishlist;

    }

    // ----------------------------
    // ----- GETTERS & SETTERS ----
    // ----------------------------
    public List<Analytic> getAnalytics() {
        return analytics;
    }

    public void setAnalytics(List<Analytic> analytics) {
        this.analytics = analytics;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddres() {
        return address;
    }

    public void setAddres(String addres) {
        this.address = addres;
    }

    public String getPreferences() {
        return preferences;
    }

    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }

    public Cart getCartId() {
        return cartId;
    }

    public void setCartId(Cart cartId) {
        this.cartId = cartId;
    }

    public List<SearchHistory> getSearchHistory() {
        return searchHistory;
    }

    public void setSearchHistory(List<SearchHistory> searchHistory) {
        this.searchHistory = searchHistory;
    }

    public Wishlist getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(Wishlist wishlistId) {
        this.wishlistId = wishlistId;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

}
