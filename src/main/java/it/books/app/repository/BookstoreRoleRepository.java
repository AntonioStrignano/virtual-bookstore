package it.books.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.books.app.model.BookstoreRole;

public interface BookstoreRoleRepository extends JpaRepository<BookstoreRole, Integer> {

}
