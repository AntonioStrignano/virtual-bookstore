package it.books.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.books.app.model.Translator;

public interface TranslatorRepository extends JpaRepository<Translator, Integer> {

}
