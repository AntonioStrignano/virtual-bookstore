package it.books.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.books.app.model.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, Integer> {

}
