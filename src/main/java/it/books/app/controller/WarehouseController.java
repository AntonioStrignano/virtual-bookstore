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

import it.books.app.model.WarehouseLocation;
import it.books.app.repository.WarehouseLocationRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/warehouse")
public class WarehouseController {

	@Autowired
	private WarehouseLocationRepository warehouseRepo;

	// ---- READ ----
	@GetMapping("")
	public String warehousePage(Model model) {

		model.addAttribute("locations", warehouseRepo.findAll());

		return "/warehouse/location-home";
	}

	// ---- CREATE ----
	// GET
	@GetMapping("/create")
	public String locationCreate(Model model) {
		WarehouseLocation newLocation = new WarehouseLocation();
		model.addAttribute("warehouseLocation", newLocation);

		return "warehouse/edit";
	}

	// POST
	@PostMapping("/create")
	public String storeNewLocation(@Valid @ModelAttribute("warehouseLocation") WarehouseLocation newLocation,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "/warehouse/edit";
		}
		warehouseRepo.save(newLocation);
		return "redirect:/warehouse";
	}

	// ---- UPDATE ----
	// GET
	@GetMapping("/edit/{id}")
	public String editLocation(Model model, @PathVariable("id") Integer id) {

		model.addAttribute("editMode", true);
		model.addAttribute("warehouseLocation", warehouseRepo.getReferenceById(id));
		return "/warehouse/edit";
	}

	// POST
	@PostMapping("/{id}/update")
	public String updateLocation(@Valid @ModelAttribute("warehouseLocation") WarehouseLocation upLocation,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "/warehouse/edit";
		}
		warehouseRepo.save(upLocation);

		return "redirect:/warehouse";
	}

	// ---- DELETE ----
	// POST
	@PostMapping("{id}/delete")
	public String deleteLocation(@PathVariable("id") Integer id) {
		warehouseRepo.deleteById(id);
		return "redirect:/warehouse";
	}

}
