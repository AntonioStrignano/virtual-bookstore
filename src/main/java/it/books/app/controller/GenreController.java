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

import it.books.app.model.Genre;
import it.books.app.repository.GenreRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/genres")
public class GenreController {

	@Autowired
	private GenreRepository genreRepo;

// -------- READ --------

	@GetMapping("")
	public String genresPage(Model model) {

		model.addAttribute("genres", genreRepo.findAll());

		return "/genres/genres-home";
	}

// -------- CREATE --------
	@GetMapping("/create")
	public String newGenre(Model model) {
		Genre newGenre = new Genre();
		model.addAttribute("genre", newGenre);
		return "/genres/edit";
	}

	@PostMapping("/create")
	public String storeNewGenre(@Valid @ModelAttribute("genre") Genre newGenre, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "/genres/edit";
		}
		genreRepo.save(newGenre);

		return "redirect:/genres";
	}

// -------- UPDATE --------
	@GetMapping("/edit/{id}")
	public String editGenre(@PathVariable("id") Integer id, Model model) {
		
		model.addAttribute("genre", genreRepo.getReferenceById(id));
		model.addAttribute("editMode", true);
		
		
		return "genres/edit";
	}
	
	@PostMapping("{id}/update")
	public String updateGenre(@Valid @ModelAttribute("genre") Genre upGenre, @PathVariable("id") Integer id, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return "genres/edit";
		}
		
		
		genreRepo.save(upGenre);
		return "redirect:/genres";
	}

// -------- DELETE --------
	
	@PostMapping("/{id}/delete")
	public String deleteGenre(@PathVariable("id") Integer id) {
		
		genreRepo.deleteById(id);
		
		return "redirect:/genres";
	}

}
