package it.books.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.books.app.model.Edition;

public interface EditionRepository extends JpaRepository<Edition, Integer> {

}
