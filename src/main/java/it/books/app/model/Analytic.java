package it.books.app.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "analytics")
public class Analytic {

	// id
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	// timestamp
	@NotEmpty(message = "Event timestamp missing.")
	@Column(name = "event_timestamp", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime eventTimestamp;

	// FKs
	// books
	// Mto
	@ManyToOne
	@JoinColumn(name = "id_book")
	private Book bookId;

	// customer id
	// MtO
	@NotNull(message = "Customer ID missing.")
	@ManyToOne
	@JoinColumn(name = "id_customer")
	private Customer customerId;

	// analytic type
	// MtO
	@NotNull(message = "Analytic Type ID missing.")
	@ManyToOne
	@JoinColumn(name = "event_type", nullable = false)
	private AnalyticType analyticType;

	// ----------------------------
	// ----- GETTERS & SETTERS ----
	// ----------------------------

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getEventTimestamp() {
		return eventTimestamp;
	}

	public void setEventTimestamp(LocalDateTime eventTimestamp) {
		this.eventTimestamp = eventTimestamp;
	}

	public Book getBookId() {
		return bookId;
	}

	public void setBookId(Book bookId) {
		this.bookId = bookId;
	}

	public Customer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Customer customerId) {
		this.customerId = customerId;
	}

	public AnalyticType getAnalyticType() {
		return analyticType;
	}

	public void setAnalyticType(AnalyticType analyticType) {
		this.analyticType = analyticType;
	}
}
