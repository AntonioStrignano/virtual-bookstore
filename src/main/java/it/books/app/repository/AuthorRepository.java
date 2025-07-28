package it.books.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.books.app.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

    public List<Author> findByMainGenreId(Integer mainGenreId);

}
