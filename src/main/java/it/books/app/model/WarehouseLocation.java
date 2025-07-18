package it.books.app.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "warehouse_locations")
public class WarehouseLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    // location
    @NotBlank(message = "Location name missing.")
    @Column(name = "location_name", nullable = false, unique = true, columnDefinition = "tinytext")
    private String location;

    // FKs
    // inventory
    // OtM
    @OneToMany(mappedBy = "warehouseLocation")
    private List<Inventory> inventoryList;

    // shop assistants
    // OtM
    @OneToMany(mappedBy = "assistantLocation")
    private List<ShopAssistant> shopAssistants;

    // CONTSTRUCTORS
    public WarehouseLocation() {
    }

    public WarehouseLocation(String location) {
        this.location = location;
    }

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
