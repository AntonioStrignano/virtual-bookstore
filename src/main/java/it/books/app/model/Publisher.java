package it.books.app.model;

import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "publisher")
public class Publisher {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	// name
	@NotBlank(message = "Publisher name missing.")
	@Column(name = "publisher_name", nullable = false, unique = true, columnDefinition = "varchar(50)")
	private String publisherName;

	// founder year
	@NotNull(message = "Publisher founder year missing.")
	@Column(name = "founder_year", nullable = false, columnDefinition = "int(4)")
	private Integer founderYear;

	// country
	@NotBlank(message = "Publisher country missing.")
	@Column(name = "country", nullable = false, columnDefinition = "varchar(30)")
	private String country;

	// city
	@Column(name = "city", columnDefinition = "varchar(30)")
	private String city;

	// website
	@Column(name = "publisher_website", columnDefinition = "mediumtext")
	private String publisherWebsite;

	// email
	@Column(name = "publisher_email", columnDefinition = "varchar(254)")
	private String email;

	// address
	@Column(name = "publisher_address", columnDefinition = "tinytext")
	private String publisherAddress;

	// bio
	@Column(name = "publisher_bio", columnDefinition = "text(2000)")
	private String publisherBio;

	// KFs

	// books
	// OtM
	@OneToMany(mappedBy = "publisher")
	private List<Book> books;

	// book collections
	// OtM
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

	public Integer getFounderYear() {
		return founderYear;
	}

	public void setFounderYear(Integer founderYear) {
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
