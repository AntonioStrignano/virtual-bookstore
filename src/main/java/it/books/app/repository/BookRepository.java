package it.books.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.books.app.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
