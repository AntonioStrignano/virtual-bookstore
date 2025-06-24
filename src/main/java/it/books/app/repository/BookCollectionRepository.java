package it.books.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.books.app.model.BookCollection;

public interface BookCollectionRepository extends JpaRepository<BookCollection, Integer> {

}
