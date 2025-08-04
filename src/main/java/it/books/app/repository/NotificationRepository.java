package it.books.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.books.app.model.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {

    public void deleteByCustomerId(Integer id);

}
