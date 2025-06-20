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

@Entity
@Table(name="analytics")
public class Analytics {

	//id
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	//timestamp
	@Column(name="event_timestamp", nullable=false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime	eventTimestamp;
	//FKs
    //books
	@ManyToOne
	@JoinColumn(name="id_book")
	private Book book;
	
	//customer id
	@ManyToOne
	@JoinColumn(name="id_customer")
	private Customer customer;

	//event type (linked list)
}
