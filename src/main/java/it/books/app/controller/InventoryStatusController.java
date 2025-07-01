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

import it.books.app.model.InventoryStatus;
import it.books.app.repository.InventoryStatusRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/inv-status")
public class InventoryStatusController {

	@Autowired
	private InventoryStatusRepository invStatRepo;

	// ---- READ ----
	@GetMapping("")
	public String invStatusPage(Model model) {

		model.addAttribute("stati", invStatRepo.findAll());
		return "inv-stat/status-home";
	}

	// ---- CREATE ----
	// GET
	@GetMapping("/create")
	public String createInvStatus(Model model) {
		InventoryStatus newStatus = new InventoryStatus();
		model.addAttribute("status", newStatus);

		return "/inv-stat/edit";
	}

	// POST
	@PostMapping("/create")
	public String storeNewStatus(@Valid @ModelAttribute("status") InventoryStatus newStatus,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "/inv-stat/edit";
		}
		invStatRepo.save(newStatus);
		return "redirect:/inv-status";
	}

	// ---- UPDATE ----
	// GET
	@GetMapping("/edit/{id}")
	public String editStatus(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("status", invStatRepo.getReferenceById(id));
		model.addAttribute("editMode", true);
		return "inv-stat/edit";
	}

	// POST
	@PostMapping("{id}/update")
	public String updateStatus(@Valid @ModelAttribute("status") InventoryStatus updateStatus,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "/inv-stat/edit";
		}
		invStatRepo.save(updateStatus);
		return "redirect:/inv-status";
	}

	// ---- DELETE ----
	// POST
	@PostMapping("/{id}/delete")
	public String deleteStatus(@PathVariable("id") Integer id) {
		invStatRepo.deleteById(id);
		return "redirect:/inv-status";
	}
}
