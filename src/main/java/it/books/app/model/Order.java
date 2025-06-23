package it.books.app.model;

import java.time.LocalDateTime;
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
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	// confirmation date
	@Column(name = "confirmation_date", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private LocalDateTime confirmationDate;

	// payment date
	@Column(name = "payment_date", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private LocalDateTime paymentDate;

	// shipping date
	@Column(name = "shipping_date", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private LocalDateTime shippingDate;

	// FKs

	// customer
	// MtO
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customerId;

	// assistant
	// MtO
	@ManyToOne
	@JoinColumn(name = "shop_assistant_id")
	private ShopAssistant shopAssistantId;

	// inventory
	// MtM
	@ManyToMany
	@JoinTable(name = "orders_inventories", joinColumns = @JoinColumn(name = "id_order"), inverseJoinColumns = @JoinColumn(name = "id_inventory"))
	private List<Inventory> inventoryOrdered;

	// ----------------------------
	// ----- GETTERS & SETTERS ----
	// ----------------------------

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getConfirmationDate() {
		return confirmationDate;
	}

	public void setConfirmationDate(LocalDateTime confirmationDate) {
		this.confirmationDate = confirmationDate;
	}

	public LocalDateTime getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDateTime paymentDate) {
		this.paymentDate = paymentDate;
	}

	public LocalDateTime getShippingDate() {
		return shippingDate;
	}

	public void setShippingDate(LocalDateTime shippingDate) {
		this.shippingDate = shippingDate;
	}

	public Customer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Customer customerId) {
		this.customerId = customerId;
	}

	public ShopAssistant getShopAssistantId() {
		return shopAssistantId;
	}

	public void setShopAssistantId(ShopAssistant shopAssistantId) {
		this.shopAssistantId = shopAssistantId;
	}

	public List<Inventory> getInventoryOrdered() {
		return inventoryOrdered;
	}

	public void setInventoryOrdered(List<Inventory> inventoryOrdered) {
		this.inventoryOrdered = inventoryOrdered;
	}

}
