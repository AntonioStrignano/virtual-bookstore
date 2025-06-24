package it.books.app.model;

import java.time.Year;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;

@Entity
@Table(name = "awards")
public class Award {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	// title
	@NotBlank(message = "Award title missing.")
	@Column(name = "award_title", nullable = false, columnDefinition = "tinytext")
	private String title;

	// year
	@PastOrPresent(message = "Award not yet declared.")
	@NotEmpty(message = "Award year missing.")
	@Column(name = "year", nullable = false, columnDefinition = "year")
	private Year year;

	// description
	@Column(name = "description", columnDefinition = "text(2000)")
	private String description;

	// award type
	@Column(name = "award_type", columnDefinition = "tinytext")
	private String awardType;

	// FKs

	// boooks
	// MtM
	@ManyToMany(mappedBy = "awards")
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Year getYear() {
		return year;
	}

	public void setYear(Year year) {
		this.year = year;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAwardType() {
		return awardType;
	}

	public void setAwardType(String awardType) {
		this.awardType = awardType;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

}
