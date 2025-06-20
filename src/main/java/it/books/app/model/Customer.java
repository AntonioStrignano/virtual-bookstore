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

@Entity
@Table(name = "customers")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	// first name
	@Column(name = "first_name", nullable = false, columnDefinition = "tinytext")
	private String firstName;

	// last name
	@Column(name = "last_name", nullable = false, columnDefinition = "tinmytext")
	private String lastName;

	// phone number
	@Column(name = "phone_number", nullable = false, columnDefinition = "varchar(20)", unique = true)
	private String phoneNumber;

	// address
	@Column(name = "address", nullable = false, columnDefinition = "tinytext")
	private String addres;

	// preferences(json)
	@Column(name = "preferences", nullable = false, columnDefinition = "JSON")
	private String preferences;

	// ----- FKs -----

	// user id
	// FK OtO
	@OneToOne
	@JoinColumn(name = "id_user")
	private User user;

	// analytics
	// FK OtM
	@OneToMany(mappedBy = "customerId")
	private List<Analytics> analytics;

	// cart
	// FK OtO
	@OneToOne
	@JoinColumn(name="id_cart")
	private Cart cartId;
	
	
	// ----------------------------
	// ----- GETTERS & SETTERS ----
	// ----------------------------

	public List<Analytics> getAnalytics() {
		return analytics;
	}

	public void setAnalytics(List<Analytics> analytics) {
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
		return addres;
	}

	public void setAddres(String addres) {
		this.addres = addres;
	}

	public String getPreferences() {
		return preferences;
	}

	public void setPreferences(String preferences) {
		this.preferences = preferences;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
