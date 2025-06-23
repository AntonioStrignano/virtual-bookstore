package it.books.app.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "formats")
public class Format {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	// name
	@Column(name = "format_name", nullable = false, unique = true, columnDefinition = "varchar(20)")
	private String name;

	// is digital
	@Column(name = "is_digital", columnDefinition = "bool")
	private Boolean isDigital;

	// FKs

	// books
	// OtM
	@OneToMany(mappedBy = "format")
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsDigital() {
		return isDigital;
	}

	public void setIsDigital(Boolean isDigital) {
		this.isDigital = isDigital;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

}
