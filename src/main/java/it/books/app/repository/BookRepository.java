package it.books.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.books.app.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
	
	Optional<Book> findByIsbn(String isbn);

}
