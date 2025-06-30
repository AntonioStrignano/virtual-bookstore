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

import it.books.app.model.Edition;
import it.books.app.repository.EditionRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/editions")
public class EditionController {

	@Autowired
	private EditionRepository editionRepo;

	// read

	@GetMapping("")
	public String editionPage(Model model) {
		model.addAttribute("editions", editionRepo.findAll());

		return "/editions/edition-home";
	}

	// create
	@GetMapping("/create")
	public String createNewEdition(Model model) {

		Edition newEdition = new Edition();
		model.addAttribute("edition", newEdition);

		return "/editions/edit";
	}

	@PostMapping("/create")
	public String saveEdition(@Valid @ModelAttribute("edition") Edition newEdition, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "/authors/edit";
		}
		editionRepo.save(newEdition);
		return "redirect:/editions";
	}

	// update
	@GetMapping("/edit/{id}")
	public String editEdition(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("editMode", true);
		model.addAttribute("edition", editionRepo.getReferenceById(id));

		return "editions/edit";
	}

	@PostMapping("/{id}/update")
	public String updateEdition(@PathVariable("id") Integer id, @Valid @ModelAttribute("edition") Edition upEdition,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "/authors/edit";
		}

		editionRepo.save(upEdition);

		return "redirect:/editions";
	}

	// delete
	@PostMapping("/{id}/delete")
	public String deleteEdition(@PathVariable("id") Integer id) {

		editionRepo.deleteById(id);
		return "redirect:/editions";
	}

}
