package it.books.app.model;

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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "inventory")
public class Inventory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	// price
	@NotNull(message = "Price missing.")
	@Column(name = "price", nullable = false, columnDefinition = "decimal(5,2)")
	private Double price;

	// quantity
	@NotBlank(message = "Quantity missing.")
	@Column(name = "quantity", nullable = false, columnDefinition = "int(5)")
	private Integer quantity;

	// notes
	@Column(name = "notes", nullable = true, columnDefinition = "varchar(200)")
	private String notes;

	// FKs

	// warehouse_location
	// Mto
	@ManyToOne
	@JoinColumn(name = "warehouse_location")
	private WarehouseLocation warehouseLocation;

	// book
	// OtO
	@OneToOne
	@JoinColumn(name = "book_id")
	private Book book;

	// inventory status
	// MtO
	@ManyToOne
	@JoinColumn(name = "inventory_status")
	private InventoryStatus inventoryStatus;

	// discount
	// MtM
	@ManyToMany
	@JoinTable(name = "inventory_discounts", joinColumns = @JoinColumn(name = "id_inventory"), inverseJoinColumns = @JoinColumn(name = "id_discount"))
	private List<Discount> discounts;

	// cart
	// MtM
	@ManyToMany(mappedBy = "inventoryList")
	private List<Cart> carts;

	// order
	// MtM
	@ManyToMany(mappedBy = "inventoryOrdered")
	private List<Order> orders;

	// ----------------------------
	// ----- GETTERS & SETTERS ----
	// ----------------------------

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public WarehouseLocation getWarehouseLocation() {
		return warehouseLocation;
	}

	public void setWarehouseLocation(WarehouseLocation warehouseLocation) {
		this.warehouseLocation = warehouseLocation;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public InventoryStatus getInventoryStatus() {
		return inventoryStatus;
	}

	public void setInventoryStatus(InventoryStatus inventoryStatus) {
		this.inventoryStatus = inventoryStatus;
	}

	public List<Cart> getCarts() {
		return carts;
	}

	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}

	public List<Discount> getDiscounts() {
		return discounts;
	}

	public void setDiscounts(List<Discount> discounts) {
		this.discounts = discounts;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

}
