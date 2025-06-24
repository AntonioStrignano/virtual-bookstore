package it.books.app.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "book")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	// title
	@Column(name = "book_title", nullable = false, columnDefinition = "tinytext")
	private String title;

	// isbn
	@Column(name = "isbn", nullable = false, columnDefinition = "char(13)", unique = true, length = 13)
	private String isbn;

	// language
	@Column(name = "lang", nullable = false, columnDefinition = "varchar(30)")
	private String language;

	// publish date
	@Column(name = "publish_date", nullable = false, columnDefinition = "date")
	private LocalDate publishDate;

	// description
	@Column(name = "description", columnDefinition = "text(2000)")
	private String description;

	// cover
	@Column(name = "cover_url", columnDefinition = "mediumtext")
	private String cover;

	// page number
	@Column(name = "pg_number", columnDefinition = "int(5)")
	private Integer page_number;


	// dimension
	@Column(name = "dimension", nullable = false, columnDefinition = "tinytext")
	private String dimension;

	// copyright
	@Column(name = "copyright", columnDefinition = "tinytext")
	private String copyright;

	// FKs

	// edition
	//MtO
	@ManyToOne
	@JoinColumn(name="edition_id")
	private Edition edition;
	
	
	// author
	// MtO
	@ManyToOne
	@JoinColumn(name = "id_author", nullable = false)
	private Author author;

	// awards
	// MtM
	@ManyToMany
	@JoinTable(name = "books_awards", joinColumns = @JoinColumn(name = "id_book"), inverseJoinColumns = @JoinColumn(name = "id_award"))
	private List<Award> awards;

	// genres
	// MtM
	@ManyToMany
	@JoinTable(name = "books_genres", joinColumns = @JoinColumn(name = "id_book"), inverseJoinColumns = @JoinColumn(name = "id_genre"))
	private List<Genre> genres;

	// publisher
	// MtO
	@ManyToOne
	@JoinColumn(name = "id_publisher", nullable = false)
	private Publisher publisher;

	// format
	// MtO
	@ManyToOne
	@JoinColumn(name = "id_format", nullable = false)
	private Format format;

	// translator
	// MtO
	@ManyToOne
	@JoinColumn(name = "id_translator")
	private Translator translator;

	// bool collection
	// MtO
	@ManyToOne
	@JoinColumn(name = "id_collection")
	private BookCollection bookCollection;

	// analytics
	// OtM
	@OneToMany(mappedBy = "bookId")
	private List<Analytic> analytics;

	// reviews
	// OtM
	@OneToMany(mappedBy = "bookId")
	private List<Review> reviews;

	// inventory
	// OtO
	@OneToOne(mappedBy = "book")
	private Inventory inventory;

	// wishlist
	// MtM
	@ManyToMany(mappedBy = "wishlistedBooks")
	private List<Wishlist> wishlists;

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

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public List<Award> getAwards() {
		return awards;
	}

	public void setAwards(List<Award> awards) {
		this.awards = awards;
	}

	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public LocalDate getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(LocalDate publishDate) {
		this.publishDate = publishDate;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public Format getFormat() {
		return format;
	}

	public void setFormat(Format format) {
		this.format = format;
	}

	public Integer getPage_number() {
		return page_number;
	}

	public void setPage_number(Integer page_number) {
		this.page_number = page_number;
	}


	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	public String getCopyright() {
		return copyright;
	}

	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}

	public Translator getTranslator() {
		return translator;
	}

	public void setTranslator(Translator translator) {
		this.translator = translator;
	}

	public BookCollection getBookCollection() {
		return bookCollection;
	}

	public void setBookCollection(BookCollection bookCollection) {
		this.bookCollection = bookCollection;
	}

	public List<Analytic> getAnalytics() {
		return analytics;
	}

	public void setAnalytics(List<Analytic> analytics) {
		this.analytics = analytics;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public List<Wishlist> getWishlists() {
		return wishlists;
	}

	public void setWishlists(List<Wishlist> wishlists) {
		this.wishlists = wishlists;
	}

	public Edition getEdition() {
		return edition;
	}

	public void setEdition(Edition edition) {
		this.edition = edition;
	}

}
