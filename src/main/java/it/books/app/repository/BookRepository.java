package it.books.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.books.app.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

    Optional<Book> findByIsbn(String isbn);

    List<Book> findByAuthorId(Integer authorId);

    List<Book> findByPublisherId(Integer publisherId);

    List<Book> findByBookCollectionId(Integer bookCollectionId);

    List<Book> findByGenres_Id(Integer genreId);

    List<Book> findByAwards_Id(Integer awardId);

    List<Book> findByTranslator_Id(Integer translatorId);
}
