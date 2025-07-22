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
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    // confirmation date
    @NotNull(message = "Order confirmation date missing.")
    @Column(name = "confirmation_date", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime confirmationDate;

    // payment date
    @Column(name = "payment_date", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime paymentDate;

    // shipping date
    @Column(name = "shipping_date", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
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

    // CONSTRUCTORS
    public Order() {
    }

    public Order(LocalDateTime confirmationDate, LocalDateTime paymentDate, LocalDateTime shippingDate,
            Customer customerId, ShopAssistant shopAssistantId, List<Inventory> inventoryOrdered) {
        this.confirmationDate = confirmationDate;
        this.paymentDate = paymentDate;
        this.shippingDate = shippingDate;
        this.customerId = customerId;
        this.shopAssistantId = shopAssistantId;
        this.inventoryOrdered = inventoryOrdered;
    }

    // ----------------------------
    // ----- GETTERS & SETTERS ----
    // ----------------------------
    public Double getTotalPrice() {
        Double totalPrice = 0.0;
        for (Inventory inventory : inventoryOrdered) {
            totalPrice += inventory.getFinalPrice();
        }
        return totalPrice;
    }

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
