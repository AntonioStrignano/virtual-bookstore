package it.books.app.dto;

import java.util.List;

import it.books.app.model.ShopAssistant;
import it.books.app.model.User;

public class UserShopAssistantDto {

    // --- User fields ---
    private String username;
    private String password;
    private Integer bookstoreRoleId = 1;
    private List<Integer> roles = List.of(1);

    // --- ShopAssistant fields ---
    private String firstName;
    private String lastName;
    private String personalEmail;
    private String internalEmail;
    private String phoneNumber;
    private String address;
    private String city;
    private Boolean isRemote;
    private Integer warehouseLocationId;  // id del WarehouseLocation

    // --- Constructors ---
    public UserShopAssistantDto() {
    }

    public UserShopAssistantDto(User user, ShopAssistant shopAssistant) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.bookstoreRoleId = user.getBookstoreRole().getId();
        this.roles = user.getRoleIds();
        this.firstName = shopAssistant.getFirstName();
        this.lastName = shopAssistant.getLastName();
        this.personalEmail = shopAssistant.getPersonalEmail();
        this.internalEmail = shopAssistant.getInternalEmail();
        this.phoneNumber = shopAssistant.getPhoneNumber();
        this.address = shopAssistant.getAddress();
        this.city = shopAssistant.getCity();
        this.isRemote = shopAssistant.getIsRemote();
        this.warehouseLocationId = shopAssistant.getAssistantLocation().getId();
    }

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

    public Integer getBookstoreRoleId() {
        return bookstoreRoleId;
    }

    public void setBookstoreRoleId(Integer bookstoreRoleId) {
        this.bookstoreRoleId = bookstoreRoleId;
    }

    public List<Integer> getRoleIds() {
        return roles;
    }

    public void setRoleIds(List<Integer> roleIds) {
        this.roles = roleIds;
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

    public String getPersonalEmail() {
        return personalEmail;
    }

    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }

    public String getInternalEmail() {
        return internalEmail;
    }

    public void setInternalEmail(String internalEmail) {
        this.internalEmail = internalEmail;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Boolean getIsRemote() {
        return isRemote;
    }

    public void setIsRemote(Boolean isRemote) {
        this.isRemote = isRemote;
    }

    public Integer getWarehouseLocationId() {
        return warehouseLocationId;
    }

    public void setWarehouseLocationId(Integer warehouseLocationId) {
        this.warehouseLocationId = warehouseLocationId;
    }
}
