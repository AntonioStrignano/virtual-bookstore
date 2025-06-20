package it.books.app.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Notification {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	// message
	@Column(name = "mnesage", nullable = false, columnDefinition = "tinytext")
	private String message;

	// read status (bool)
	@Column(name="read_status", nullable = false, columnDefinition = "bool")
	private Boolean isRead;
	
	// creation date
	@Column(name="creation_date", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private LocalDateTime creationDate;

	// FKs (da integrare)

	// customer id

	// notification type

}
