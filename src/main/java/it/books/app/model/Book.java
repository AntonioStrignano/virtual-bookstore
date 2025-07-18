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
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "book")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	// title
	@NotBlank(message = "Book title empty.")
	@Column(name = "book_title", nullable = false, columnDefinition = "tinytext")
	private String title;

	// isbn
	@NotEmpty(message = "ISBN missing.")
	@Column(name = "isbn", nullable = false, columnDefinition = "char(13)", unique = true, length = 13)
	private String isbn;

	// language
	@NotEmpty(message = "Book language missing.")
	@Column(name = "lang", nullable = false, columnDefinition = "varchar(30)")
	private String language;

	// publish date
	@NotNull(message = "Book publish date missing.")
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
	private Integer pageNumber;

	// dimension
	@Column(name = "dimension", nullable = false, columnDefinition = "tinytext")
	private String dimension;

	// copyright
	@Column(name = "copyright", columnDefinition = "tinytext")
	private String copyright;

	// FKs

	// edition
	// MtO
	@ManyToOne
	@JoinColumn(name = "edition_id")
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

	// book collection
	// MtO
	@ManyToOne
	@JoinColumn(name = "id_collection")
	private BookCollection bookCollection;

	// analytics
	// OtM
	@OneToMany(mappedBy = "bookId")
	private List<Analytic> analytics;

	// inventory
	// OTM
	@OneToMany(mappedBy = "book")
	private List<Inventory> storeRecords;

	// reviews
	// OtM
	@OneToMany(mappedBy = "bookId")
	private List<Review> reviews;

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

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
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

	public List<Wishlist> getWishlists() {
		return wishlists;
	}

	public void setWishlists(List<Wishlist> wishlists) {
		this.wishlists = wishlists;
	}

	public List<Inventory> getStoreRecords() {
		return storeRecords;
	}

	public void setStoreRecords(List<Inventory> storeRecords) {
		this.storeRecords = storeRecords;
	}

	public Edition getEdition() {
		return edition;
	}

	public void setEdition(Edition edition) {
		this.edition = edition;
	}

}
