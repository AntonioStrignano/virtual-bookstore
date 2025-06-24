package it.books.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.books.app.model.Genre;

public interface GenreRepository extends JpaRepository<Genre, Integer> {

}
