package it.books.app.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "inventory")
public class Inventory {

    public List<Discount> getDiscountList() {
        return discountList;
    }

    public void setDiscountList(List<Discount> discountList) {
        this.discountList = discountList;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    // price
    @NotNull(message = "Price missing.")
    @Column(name = "price", nullable = false, columnDefinition = "decimal(5,2)")
    private Double price;

    // quantity
    @NotNull(message = "Quantity missing.")
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

    // inventory status
    // MtO
    @ManyToOne
    @JoinColumn(name = "inventory_status")
    private InventoryStatus inventoryStatus;

    // discount
    // MtM
    @ManyToMany(mappedBy = "inventoryList")
    private List<Discount> discountList;

    // book
    // MtO
    @ManyToOne
    @JoinColumn(name = "books")
    private Book book;

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
    public Double getFinalPrice() {
        Double finalPrice = price;
        for (Discount discount : discountList) {
            if (discount.getStartDate().isAfter(LocalDateTime.now()) || discount.getEndDate().isBefore(LocalDateTime.now())) {
                continue; // Skip discounts that are not active
            }
            switch (discount.getDiscountType().getId()) {
                case 1: // Percentage discount
                    finalPrice -= finalPrice * (discount.getDiscountValue() / 100);
                    break;
                case 2: // Fixed amount discount
                    finalPrice -= discount.getDiscountValue();
                    break;
            }
        }
        return finalPrice;
    }

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
        return discountList;
    }

    public void setDiscounts(List<Discount> discounts) {
        this.discountList = discounts;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

}
