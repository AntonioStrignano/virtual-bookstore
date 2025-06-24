package it.books.app.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "translators")
public class Translator {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	// first name
	@Column(name = "first_name", nullable = false, columnDefinition = "tinytext")
	private String firstName;

	// last name
	@Column(name = "last_name", nullable = false, columnDefinition = "tinytext")
	private String lastName;

	// birth date
	@Column(name = "birth_date", nullable = false, columnDefinition = "date")
	private LocalDate birthDate;

	// death date
	@Column(name = "death_date", nullable = true, columnDefinition = "date")
	private LocalDate deathDate;

	// nationality
	@Column(name = "nationality", nullable = false, columnDefinition = "varchar(30)")
	private String nationality;

	// bio
	@Column(name = "bio", columnDefinition = "varchar(2000)")
	private String bio;

	// website
	@Column(name = "website", columnDefinition = "mediumtext")
	private String website;

	// pro pic
	@Column(name = "pro_pic", columnDefinition = "mediumtext")
	private String profilePicture;

	// social media
	@Column(name = "social_media", columnDefinition = "mediumtext")
	private String socialMedia;

	// is active
	@Column(name = "is_active", nullable = false, columnDefinition = "bool")
	private Boolean isActive;

	// FKs

	// books
	// OtM
	@OneToMany(mappedBy = "translator")
	private List<Book> books;

	// ----------------------------
	// ----- GETTERS & SETTERS ----
	// ----------------------------
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

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public LocalDate getDeathDate() {
		return deathDate;
	}

	public void setDeathDate(LocalDate deathDate) {
		this.deathDate = deathDate;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public String getSocialMedia() {
		return socialMedia;
	}

	public void setSocialMedia(String socialMedia) {
		this.socialMedia = socialMedia;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

}
