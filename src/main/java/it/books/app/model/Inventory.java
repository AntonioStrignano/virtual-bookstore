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

@Entity
@Table(name = "inventory")
public class Inventory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	// price
	@Column(name = "price", nullable = false, columnDefinition = "decimal(5,2)")
	private Double price;

	// quantity
	@Column(name = "quantity", nullable = false, columnDefinition = "int(5)")
	private Integer quantity;

	// warehouse_location
	@Column(name = "wh_location", nullable = true, columnDefinition = "varchar(40)")
	private String warehouseLocation;

	// notes
	@Column(name = "notes", nullable = true, columnDefinition = "varchar(200)")
	private String notes;

	// FKs
	// book
	// OtO
	@OneToOne
	@JoinColumn(name = "book_id")
	private Book book;

	// status
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
}
