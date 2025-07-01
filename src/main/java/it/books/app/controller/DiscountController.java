package it.books.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import it.books.app.repository.DiscountRepository;

@Controller
@RequestMapping("/discounts")
public class DiscountController {

	@Autowired
	private DiscountRepository discountRepo;
}
