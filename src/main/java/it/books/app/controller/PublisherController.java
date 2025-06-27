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

import it.books.app.model.Publisher;
import it.books.app.repository.PublisherRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/publishers")
public class PublisherController {

	@Autowired
	private PublisherRepository publRepo;

// -------- READ --------
	@GetMapping("")
	public String publishersPage(Model model) {

		model.addAttribute("publishers", publRepo.findAll());
		return "/publishers/publ-home";
	}

// -------- CREATE --------
	// GET
	@GetMapping("/create")
	public String newPubl(Model model) {

		Publisher newPubl = new Publisher();
		model.addAttribute("publ", newPubl);
		return "/publishers/edit";
	}

	// POST
	@PostMapping("/create")
	public String storeNewPubl(@Valid @ModelAttribute("publ") Publisher newPubl, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "/publishers/edit";
		}
		publRepo.save(newPubl);
		return "redirect:/publishers";
	}

// -------- UPDATE --------
	// GET
	@GetMapping("/edit/{id}")
	public String editPubl(Model model, @PathVariable("id") Integer id) {

		model.addAttribute("publ", publRepo.getReferenceById(id));
		model.addAttribute("editMode", true);

		return "/publishers/edit";
	}

	// POST
	@PostMapping("/{id}/update")
	public String updatePubl(@Valid @ModelAttribute("publ") Publisher upPubl, @PathVariable("id") Integer id,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "publishers/edit";
		}

		publRepo.save(upPubl);
		return "redirect:/publishers";
	}

// -------- DELETE --------

	@PostMapping("/{id}/delete")
	public String deletePubl(@PathVariable("id") Integer id) {

		publRepo.deleteById(id);

		return "redirect:/publishers";
	}

}
