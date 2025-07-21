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
@Table(name = "notification_types")
public class NotificationType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    // name
    @NotBlank(message = "Notification name missing.")
    @Column(name = "notification_name", nullable = false, unique = true, columnDefinition = "VARCHAR(50)")
    private String notificationName;

    // template
    @NotBlank(message = "Notification template missing.")
    @Column(name = "notif_template", nullable = true, columnDefinition = "tinytext")
    private String notificationTemplate;

    // FKs
    // notifications
    // OtM
    @OneToMany(mappedBy = "notificationType")
    private List<Notification> notifications;

    // CONSTRUCTORS 
    public NotificationType() {
    }

    public NotificationType(String notificationName, String notificationTemplate) {
        this.notificationName = notificationName;
        this.notificationTemplate = notificationTemplate;
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

    public String getNotificationName() {
        return notificationName;
    }

    public void setNotificationName(String notificationName) {
        this.notificationName = notificationName;
    }

    public String getNotificationTemplate() {
        return notificationTemplate;
    }

    public void setNotificationTemplate(String notificationTemplate) {
        this.notificationTemplate = notificationTemplate;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

}
