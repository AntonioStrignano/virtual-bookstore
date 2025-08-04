package it.books.app.dto;

import java.util.List;

public class UserCustomerDto {

    // --- User fields ---
    private String username;
    private String password;
    private Integer bookstoreRole = 2;
    private List<Integer> roles = List.of(2);

    // --- Customer fields ---
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String preferences;           // JSON string
    private Integer cartId;               // id del Cart
    private Integer wishlistId;           // id della Wishlist

    // --- Getters & Setters ---
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

    public Integer getBookstoreRole() {
        return bookstoreRole;
    }

    public void setBookstoreRole(Integer bookstoreRole) {
        this.bookstoreRole = bookstoreRole;
    }

    public List<Integer> getRoles() {
        return roles;
    }

    public void setRoles(List<Integer> roles) {
        this.roles = roles;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPreferences() {
        return preferences;
    }

    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Integer getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(Integer wishlistId) {
        this.wishlistId = wishlistId;
    }
}
