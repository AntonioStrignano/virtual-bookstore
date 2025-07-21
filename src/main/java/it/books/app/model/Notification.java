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
import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    // message
    @NotBlank(message = "Notification message missing.")
    @Column(name = "mnesage", nullable = false, columnDefinition = "tinytext")
    private String message;

    // read status (bool)
    @AssertFalse(message = "Notification must be unread at the creation.")
    @Column(name = "read_status", nullable = false, columnDefinition = "bool")
    private Boolean isRead;

    // creation date
    @NotNull(message = "Motification creation date missing.")
    @Column(name = "creation_date", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime creationDate;

    @Column(name = "notification_hypertext", nullable = false, columnDefinition = "mediumtext")
    private String hypertext;

    // FKs
    // customer id
    // MtO
    @ManyToOne
    @JoinColumn(name = "id_customer", nullable = false)
    private Customer customerId;

    // notification type
    // MtO
    @ManyToOne
    @JoinColumn(name = "notification_type", nullable = false)
    private NotificationType notificationType;

    // book reference
    @ManyToOne
    @JoinColumn(name = "book_reference")
    private Book bookRef;

    //order reference
    @ManyToOne
    @JoinColumn(name = "order_reference")
    private Order orderRef;

    // CONSTRUCTOR
    // ----------------------------
    // ----- GETTERS & SETTERS ----
    // ----------------------------
    public Integer getId() {
        return id;
    }

    public Notification(LocalDateTime creationDate, Customer customerId,
            NotificationType notificationType, Book bookRef, Order orderRef) {
        this.isRead = false;
        this.creationDate = creationDate;
        this.customerId = customerId;
        this.notificationType = notificationType;
        this.bookRef = bookRef;
        this.message = notificationType.getNotificationTemplate() + "(" + bookRef.getTitle() + ")";
        String hypertext = notificationType.getNotificationLink();
        hypertext.replace("{bookId}", "" + bookRef.getId());
        hypertext.replace("{orderId}", "" + orderRef.getId());
        this.hypertext = hypertext;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Customer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public Book getBookRef() {
        return bookRef;
    }

    public void setBookRef(Book bookRef) {
        this.bookRef = bookRef;
    }

    public String getHypertext() {
        return hypertext;
    }

    public void setHypertext(String hypertext) {
        this.hypertext = hypertext;
    }

    public Order getOrderRef() {
        return orderRef;
    }

    public void setOrderRef(Order orderRef) {
        this.orderRef = orderRef;
    }

}
