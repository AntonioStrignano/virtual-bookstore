package it.books.app.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "discounts")
public class Discount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	// name
	@NotBlank(message = "Name empty.")
	@Column(name = "discount_name", nullable = false, columnDefinition = "tinytext")
	private String name;

	// start date
	@DateTimeFormat(pattern = "yyyy-MM-dd hh-mm")
	@NotNull(message = "Discount start date missing")
	@Column(name = "start_date", nullable = false, columnDefinition = "datetime")
	private LocalDateTime startDate;

	// end date
	@DateTimeFormat(pattern = "yyyy-MM-dd hh-mm")
	@NotNull(message = "Discount end date missing.")
	@Column(name = "end_date", nullable = false, columnDefinition = "datetime")
	private LocalDateTime endDate;

	// value
	@NotNull(message = "Missing discount value.")
	@Column(name = "discount_value", nullable = false, columnDefinition = "tinyint")
	private Integer discountValue;

	// FKs
	// inventory
	// MtM
	@ManyToMany(mappedBy = "discounts")
	private List<Inventory> inventoryList;

	// discount type
	// MtO
	@ManyToOne
	@NotNull(message = "Select a type")
	@JoinColumn(name = "discount_type", nullable = false)
	private DiscountType discountType;

	// ----------------------------
	// ----- GETTERS & SETTERS ----
	// ----------------------------

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public List<Inventory> getInventoryList() {
		return inventoryList;
	}

	public void setInventoryList(List<Inventory> inventoryList) {
		this.inventoryList = inventoryList;
	}

	public DiscountType getDiscountType() {
		return discountType;
	}

	public void setDiscountType(DiscountType discountType) {
		this.discountType = discountType;
	}

	public Integer getDiscountValue() {
		return discountValue;
	}

	public void setDiscountValue(Integer discountValue) {
		this.discountValue = discountValue;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
