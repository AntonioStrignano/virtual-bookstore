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
@Table(name = "shop_assistants")
public class ShopAssistant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    // first name
    @NotBlank(message = "Shop assistant first name missing.")
    @Column(name = "first_name", nullable = false, columnDefinition = "VARCHAR(30)")
    private String firstName;

    // last name
    @NotBlank(message = "Shop assistant last name missing.")
    @Column(name = "last_name", nullable = false, columnDefinition = "VARCHAR(30)")
    private String lastName;

    // personal email
    @NotBlank(message = "Shop assistant personal mail missing.")
    @Column(name = "personal_email", nullable = false, columnDefinition = "VARCHAR (250)")
    private String personalEmail;

    // internal email
    @NotBlank(message = "Shop assistant internal email missing.")
    @Column(name = "internal_email", nullable = false, unique = true, columnDefinition = "VARCHAR(250)")
    private String internalEmail;

    // phone number
    @NotBlank(message = "Shop assistant phone number missing.")
    @Column(name = "phone_number", nullable = false, columnDefinition = "VARCHAR(15)")
    private String phoneNumber;

    // address
    @NotBlank(message = "Shop assistant address missing.")
    @Column(name = "address", nullable = false, columnDefinition = "VARCHAR(250)")
    private String address;

    // city
    @NotBlank(message = "Shop assistant city missing.")
    @Column(name = "city", nullable = false, columnDefinition = "VARCHAR(50)")
    private String city;

    // is remote
    @NotEmpty(message = "Shop assistant remote flag missing.")
    @Column(name = "is_remote", nullable = false, columnDefinition = "BOOL")
    private Boolean isRemote;

    @Column(name = "is_fired", columnDefinition = "BOOLEAN")
    private Boolean isFired;

    // FKs
    // order
    // OtM
    @OneToMany(mappedBy = "shopAssistantId")
    private List<Order> orders;

    // warehouse location
    // MtO
    @ManyToOne
    @JoinColumn(name = "assistant_location")
    private WarehouseLocation assistantLocation;

    // ----------------------------
    // ----- GETTERS & SETTERS ----
    // ----------------------------
    public ShopAssistant() {
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

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public WarehouseLocation getAssistantLocation() {
        return assistantLocation;
    }

    public void setAssistantLocation(WarehouseLocation assistantLocation) {
        this.assistantLocation = assistantLocation;
    }

    public Boolean getIsFired() {
        return isFired;
    }

    public void setIsFired(Boolean isFired) {
        this.isFired = isFired;
    }

}
