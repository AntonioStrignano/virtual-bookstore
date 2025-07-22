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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "search_history")
public class SearchHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    // search query
    @NotBlank(message = "Search history query missing.")
    @Column(name = "search_query", nullable = false, columnDefinition = "tinytext")
    private String searchQuery;

    // search date
    @NotNull(message = "Search history date missing.")
    @Column(name = "search_date", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime searchDate;

    // FKs
    // customer id
    // MtO
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customerId;

    // CONSTRUCTORS
    public SearchHistory() {
    }

    public SearchHistory(String searchQuery, LocalDateTime searchDate, Customer customerId) {
        this.searchQuery = searchQuery;
        this.searchDate = searchDate;
        this.customerId = customerId;
    }

    //////////////////////////
	// GETTERS & SETTERS //
	//////////////////////////

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    public LocalDateTime getSearchDate() {
        return searchDate;
    }

    public void setSearchDate(LocalDateTime searchDate) {
        this.searchDate = searchDate;
    }

    public Customer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
    }

}
