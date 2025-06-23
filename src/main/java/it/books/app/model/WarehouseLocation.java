package it.books.app.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "warehouse_locations")
public class WarehouseLocation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "lcation_name", nullable = false, unique = true, columnDefinition = "tinytext")
	private String location;

	// inventory OtM (mapped by "warehouseLocation")
	@OneToMany(mappedBy = "warehouseLocation")
	private List<Inventory> inventoryList;

	// shop assistants OtM
	@OneToMany(mappedBy = "assistantLocation")
	private List<ShopAssistant> shopAssistants;

	// ----------------------------
	// ----- GETTERS & SETTERS ----
	// ----------------------------

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<Inventory> getInventoryList() {
		return inventoryList;
	}

	public void setInventoryList(List<Inventory> inventoryList) {
		this.inventoryList = inventoryList;
	}

	public List<ShopAssistant> getShopAssistants() {
		return shopAssistants;
	}

	public void setShopAssistants(List<ShopAssistant> shopAssistants) {
		this.shopAssistants = shopAssistants;
	}

}
