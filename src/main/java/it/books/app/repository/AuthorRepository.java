package it.books.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.books.app.model.Author;
import it.books.app.model.Genre;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

	public List<Author> findByMainGenre(Genre mainGenre);

	public List<Author> findByIsActiveTrue();

	public List<Author> findByIsActiveFalse();

	public List<Author> findByLastName(String lastName);

}
