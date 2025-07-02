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

import it.books.app.model.Discount;
import it.books.app.repository.DiscountRepository;
import it.books.app.repository.DiscountTypeRepository;
import it.books.app.repository.InventoryRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/discounts")
public class DiscountController {

	@Autowired
	private DiscountRepository discountRepo;

	@Autowired
	private InventoryRepository invRepo;

	@Autowired
	private DiscountTypeRepository discTypeRepo;

	// ---- READ ----
	// GET
	@GetMapping("")
	public String discountPage(Model model) {

		model.addAttribute("discounts", discountRepo.findAll());
		return "discounts/discount-home";
	}

	// ---- CREATE ----
	// GET
	@GetMapping("/create")
	public String createDiscount(Model model) {

		Discount newDiscount = new Discount();
		model.addAttribute("discount", newDiscount);
		model.addAttribute("stock", invRepo.findAll());
		model.addAttribute("types", discTypeRepo.findAll());
		return "/discounts/edit";
	}

	// POST
	@PostMapping("create")
	public String storeNewDiscount(@Valid @ModelAttribute("discount") Discount newDiscount,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "/discounts/edit";
		}
		discountRepo.save(newDiscount);
		return "redirect:/discounts";
	}

	// ---- UPDATE ----
	// GET
	@GetMapping("/edit/{id}")
	public String editDiscount(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("editMode", true);
		model.addAttribute("discount", discountRepo.getReferenceById(id));
		model.addAttribute("stock", invRepo.findAll());
		model.addAttribute("types", discTypeRepo.findAll());
		return "discounts/edit";
	}

	// POST
	@PostMapping("{id}/update")
	public String updateDiscount(@Valid @ModelAttribute("discount") Discount upDiscount, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "/discounts/edit";
		}
		discountRepo.save(upDiscount);
		return "redirect:/discounts";
	}

	// ---- DELETE ----
	@PostMapping("{id}/delete")
	public String deleteDiscount(@PathVariable("id") Integer id) {

		discountRepo.deleteById(id);
		return "redirect:/discounts";
	}
}
