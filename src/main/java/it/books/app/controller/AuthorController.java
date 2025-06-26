package it.books.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.books.app.model.Author;
import it.books.app.repository.AuthorRepository;
import it.books.app.repository.GenreRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/authors")
public class AuthorController {

	@Autowired
	private AuthorRepository authorRepo;

	@Autowired
	private GenreRepository genreRepo;

// -------- READ ---------
	@GetMapping("")
	public String authorPage(Model model) {
		model.addAttribute("authors", authorRepo.findAll());
		model.addAttribute("genres", genreRepo.findAll());
		return "authors/author-home";
	}

// -------- CREATE --------	
	// GET
	@GetMapping("/create")
	public String newAuthor(Model model) {

		Author newAuthor = new Author();
		model.addAttribute("author", newAuthor);
		model.addAttribute("genres", genreRepo.findAll());

		return "/authors/edit";
	}

	// POST
	@PostMapping("/create")
	public String storeNewAuthor(@Valid @ModelAttribute("author") Author newAuthor, BindingResult bindingResult,
			Model model) {

		model.addAttribute("genres", genreRepo.findAll());

		if (bindingResult.hasErrors()) {
			return "/authors/edit";
		}
		authorRepo.save(newAuthor);

		return "redirect:/authors";
	}

//	-------- UPDATE	--------
	// GET
	@GetMapping("/edit/{id}")
	public String editAuthor(@PathVariable("id") Integer id, Model model) {

		model.addAttribute("author", authorRepo.getReferenceById(id));
		model.addAttribute("genres", genreRepo.findAll());
		model.addAttribute("editMode", true);

		return "authors/edit";
	}

	// POST
	@PostMapping("/{id}/update")
	public String updateAuthor(@Valid @ModelAttribute("author") Author upAuthor, @PathVariable("id") Integer id,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "authors/edit";
		}

		authorRepo.save(upAuthor);

		return "redirect:/authors";
	}

//	--------	DELETE	--------
	
	//	POST
	@PostMapping("/{id}/delete")
	public String deleteAuthor(@PathVariable("id") Integer id) {
		
		authorRepo.deleteById(id);
		
		return "redirect:/authors";
	}
	
	
}
