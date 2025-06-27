package it.books.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.books.app.repository.AuthorRepository;
import it.books.app.repository.AwardRepository;
import it.books.app.repository.BookRepository;
import it.books.app.repository.EditionRepository;
import it.books.app.repository.FormatRepository;
import it.books.app.repository.GenreRepository;
import it.books.app.repository.PublisherRepository;
import it.books.app.repository.TranslatorRepository;

@Controller
@RequestMapping("/books")
public class BookController {

	@Autowired
	private BookRepository bookRepo;
	
	@Autowired
	private AuthorRepository authorRepository;
	
//	@Autowired
//	private EditionRepository editionRepo;
	
//	@Autowired
//	private AwardRepository awardRepo;
	
	@Autowired
	private GenreRepository genreRepo;
	
	@Autowired
	private PublisherRepository publRepo;
	
	@Autowired
	private FormatRepository formatRepo;
	
//	@Autowired
//	private TranslatorRepository translRepo;
	
	
	
	
	
	
	@GetMapping("")
	public String bookCatalog(Model model) {
		model.addAttribute("books", bookRepo.findAll());
		
		return "/books/book-home";
	}
	
	
	
	@GetMapping("/create")
	public String newBook() {
		return "";
	}
}
