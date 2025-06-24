package it.books.app.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "genres")
public class Genre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	// name
	@Column(name = "genre_name", nullable = false, unique = true, columnDefinition = "varchar(30)")
	private String name;

	// description
	@Column(name = "description", columnDefinition = "varchar(100)")
	private String description;

	// FKs

	// books
	// MtM
	@ManyToMany(mappedBy = "genres")
	private List<Book> books;

	// authors
	// OtM
	@OneToMany(mappedBy = "mainGenre")
	private List<Author> authors;

	// ----------------------------
	// ----- GETTERS & SETTERS ----
	// ----------------------------
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

}
