package it.books.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.books.app.model.NotificationType;

public interface NotificationTypeRepository extends JpaRepository<NotificationType, Integer> {

}
