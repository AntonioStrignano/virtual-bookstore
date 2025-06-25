package it.books.app.service;

import java.util.List;

import it.books.app.model.Author;

public interface AuthorService {

	public List<Author> findAll();

	public List<Author> findByIsActiveTrue();

	public List<Author> findByIsActiveFalse();

	public List<Author> findByLastName(String lastName);

	public List<Author> findByMainGenre(Integer genreId);

}
