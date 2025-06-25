package it.books.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.books.app.model.Author;
import it.books.app.model.Genre;
import it.books.app.repository.AuthorRepository;
import it.books.app.repository.GenreRepository;

@Service
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	private AuthorRepository authorRepo;

	@Autowired
	private GenreRepository genreRepo;
	
	@Override
	public List<Author> findAll() {
		return authorRepo.findAll();
	}

	@Override
	public List<Author> findByMainGenre(Integer GenreId) {
		Genre mainGenre = genreRepo.getReferenceById(GenreId);
		return authorRepo.findByMainGenre(mainGenre);
	}

	@Override
	public List<Author> findByLastName(String authorLastName) {
		return authorRepo.findByLastName(authorLastName);
	}

	@Override
	public List<Author> findByIsActiveTrue() {
		return authorRepo.findByIsActiveTrue();
	}

	@Override
	public List<Author> findByIsActiveFalse() {
		return authorRepo.findByIsActiveFalse();
	}

}
