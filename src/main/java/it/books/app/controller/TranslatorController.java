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

import it.books.app.model.Translator;
import it.books.app.repository.TranslatorRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/translators")
public class TranslatorController {

	@Autowired
	private TranslatorRepository translRepo;

	// ---- READ ----
	@GetMapping("")
	public String translPage(Model model) {

		model.addAttribute("translators", translRepo.findAll());

		return "/translators/transl-home";
	}

	// ---- CREATE ----
	// GET
	@GetMapping("/create")
	public String newTransl(Model model) {

		Translator newTransl = new Translator();
		model.addAttribute("transl", newTransl);

		return "/translators/edit";
	}

	// POST
	@PostMapping("/create")
	public String createNewTransl(@Valid @ModelAttribute("transl") Translator newTransl, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "/translator/edit";
		}
		translRepo.save(newTransl);
		return "redirect:/translators";
	}

	// ---- UPDATE ----

	// GET
	@GetMapping("/edit/{id}")
	public String editTransl(@PathVariable("id") Integer id, Model model) {

		model.addAttribute("editMode", true);
		model.addAttribute("transl", translRepo.getReferenceById(id));

		return "translators/edit";
	}

	// POST
	@PostMapping("{id}/update")
	public String updateTransl(@Valid @ModelAttribute("transl") Translator upTransl, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "/translator/edit";
		}
		translRepo.save(upTransl);

		return "redirect:/translators";

	}

	// ---- DELETE ----
	@PostMapping("{id}/delete")
	public String deleteTransl(@PathVariable("id") Integer id) {

		translRepo.deleteById(id);
		return "redirect:/translators";
	}
}
