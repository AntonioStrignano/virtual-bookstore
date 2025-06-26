package it.books.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/books")
public class BookController {

	@GetMapping("")
	public String bookCatalog() {
		return "/books/book-home";
	}
	
	
/*
 * Entita' dipendenti:
 * V	author (if delete author, delete author record in every book)
 * V	genres (if delete genre, drop to a default blank genre)
 * X	publisher
 * X	format
 * X	inventory
 * 	
 */
	
	@GetMapping("/create")
	public String newBook() {
		return "";
	}
}
