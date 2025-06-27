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

import it.books.app.model.Format;
import it.books.app.repository.FormatRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/formats")
public class FormatController {

	@Autowired
	private FormatRepository formatRepo;

//	-------- READ --------
	@GetMapping("")
	public String formatPage(Model model) {
		model.addAttribute("formats", formatRepo.findAll());

		return "/formats/format-home";
	}

//	-------- CREATE --------
	// GET
	@GetMapping("/create")
	public String newFormat(Model model) {
		Format newFormat = new Format();
		model.addAttribute("format", newFormat);
		return "formats/edit";
	}

	@PostMapping("/create")
	public String saveNewFormat(@Valid @ModelAttribute("format") Format newFormat, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "/formats/edit";
		}

		formatRepo.save(newFormat);
		return "redirect:/formats";
	}

//	-------- UPDATE --------
	// GET
	@GetMapping("/edit/{id}")
	public String editFormat(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("editMode", true);
		model.addAttribute("format", formatRepo.getReferenceById(id));
		return "/formats/edit";
	}

	// POST
	@PostMapping("/{id}/update")
	public String updateFormat(@Valid @ModelAttribute("format") Format upFormat, BindingResult bindingResult,
			@PathVariable("id") Integer id) {
		if (bindingResult.hasErrors()) {
			return "/formats/edit";
		}
		formatRepo.save(upFormat);
		return "redirect:/formats";
	}

//	-------- DELETE --------
	@PostMapping("{id}/delete")
	public String deleteFormat(@PathVariable("id") Integer id) {

		formatRepo.deleteById(id);

		return "redirect:/formats";
	}

}
