package it.books.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.books.app.model.Format;

public interface FormatRepository extends JpaRepository<Format, Integer> {

}
