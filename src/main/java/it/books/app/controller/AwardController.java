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

import it.books.app.model.Award;
import it.books.app.repository.AwardRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/awards")
public class AwardController {

	@Autowired
	private AwardRepository awardRepo;

	// ---- READ ----
	@GetMapping("")
	public String awardPage(Model model) {
		model.addAttribute("awards", awardRepo.findAll());
		return "/awards/award-home";
	}

	// ---- CREATE ----

	// GET
	@GetMapping("/create")
	public String createNewAward(Model model) {
		Award newAward = new Award();
		model.addAttribute("award", newAward);
		return "/awards/edit";
	}

	// POST
	@PostMapping("/create")
	public String storeNewAward(@Valid @ModelAttribute("award") Award newAward, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "/awards/edit";
		}

		awardRepo.save(newAward);
		return "redirect:/awards";
	}

	// ---- UPDATE ----

	// GET
	@GetMapping("/edit/{id}")
	public String editAward(@PathVariable("id") Integer id, Model model) {

		model.addAttribute("editMode", true);
		model.addAttribute("award", awardRepo.getReferenceById(id));

		return "/awards/edit";
	}

	// POST
	@PostMapping("{id}/update")
	public String storeUpdateAward(@Valid @ModelAttribute("award") Award upAward, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "/awards/edit";
		}

		awardRepo.save(upAward);

		return "redirect:/awards";
	}

	// ---- DELETE ----
	@PostMapping("/{id}/delete")
	public String deleteAward(@PathVariable("id") Integer id) {

		awardRepo.deleteById(id);
		return "redirect:/awards";
	}

}
