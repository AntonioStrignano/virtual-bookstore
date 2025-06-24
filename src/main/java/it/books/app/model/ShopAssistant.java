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

@Entity
@Table(name = "shop_assistants")
public class ShopAssistant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	// first name
	@Column(name = "first_name", nullable = false, columnDefinition = "VARCHAR(30)")
	private String firstName;

	// last name
	@Column(name = "last_name", nullable = false, columnDefinition = "VARCHAR(30)")
	private String lastName;

	// personal email
	@Column(name = "personal_email", nullable = false, columnDefinition = "VARCHAR (250)")
	private String personalEmail;

	// internal email
	@Column(name = "internal_email", nullable = false, unique = true, columnDefinition = "VARCHAR(250)")
	private String internalEmail;

	// phone number
	@Column(name = "phone_number", nullable = false, columnDefinition = "VARCHAR(15)")
	private String phoneNumber;

	// address
	@Column(name = "address", nullable = false, columnDefinition = "VARCHAR(250)")
	private String address;

	// city
	@Column(name = "city", nullable = false, columnDefinition = "VARCHAR(50)")
	private String city;

	// is remote
	@Column(name = "is_remote", nullable = false, columnDefinition = "BOOL")
	private Boolean isRemote;

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
		// TODO Auto-generated constructor stub
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

}
