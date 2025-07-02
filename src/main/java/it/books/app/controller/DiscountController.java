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
import it.books.app.repository.BookRepository;
import it.books.app.repository.DiscountRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/discounts")
public class DiscountController {

	@Autowired
	private DiscountRepository discountRepo;
	
	@Autowired
	private BookRepository bookRepo;

	// ---- READ ----
	// GET
	@GetMapping("")
	public String discountPage(Model model) {

		model.addAttribute("discounts", discountRepo.findAll());
		model.addAttribute("books", bookRepo.findAll());
		return "discounts/discount-home";
	}

	// ---- CREATE ----
	// GET
	@GetMapping("/create")
	public String createDiscount(Model model) {

		Discount newDiscount = new Discount();
		model.addAttribute("discount", newDiscount);
		return "/discounts/edit";
	}

	// POST
	@PostMapping("create")
	public String storeNewDiscount(@Valid @ModelAttribute("discount") Discount newDiscount,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "/disc-types/edit";
		}
		discountRepo.save(newDiscount);
		return "redirect:/discounts";
	}

	// ---- UPDATE ----
	// GET
	@GetMapping("/edit/{id}")
	public String editDiscount(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("editMode", true);
		model.addAttribute("discount", discountRepo.findById(id).get());
		return "discounts/edit";
	}

	// POST
	@PostMapping("{id}/update")
	public String updateDiscount(@Valid @ModelAttribute("discount") Discount upDiscount, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "/disc-types/edit";
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
