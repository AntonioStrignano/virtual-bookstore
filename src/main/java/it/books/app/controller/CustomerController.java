package it.books.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.books.app.model.Customer;
import it.books.app.repository.CustomerRepository;
import it.books.app.repository.UserRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository custRepo;

    @Autowired
    private UserRepository userRepo;

// ---- READ ----
    @GetMapping("")
    public String pageCustomer(Model model) {
        model.addAttribute("customers", custRepo.findAll());
        return "customers/home";
    }

// ---- CREATE ----
    @GetMapping("/new/{userId}")
    public String newCustomer(@PathVariable("userId") Integer userId, Model model) {

        Customer newCustomer = new Customer();
        userRepo.getReferenceById(userId).setSecondId(newCustomer.getId());
        model.addAttribute("customer", newCustomer);

        return "customers/edit";
    }

    @PostMapping("/create")
    public String createCustomer(@Valid @ModelAttribute("customer") Customer newCustomer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "customers/edit";
        }
        custRepo.save(newCustomer);
        return "redirect:/customers";
    }

// ---- UPDATE ----
    @GetMapping("/edit/{userId}")
    public String editCustomer(@PathVariable("userId") Integer userId, Model model) {
        model.addAttribute("customer", custRepo.getReferenceById(userRepo.getReferenceById(userId).getSecondId()));
        model.addAttribute("editMode", true);
        return "customers/edit";
    }

    @PostMapping("{id}/update")
    public String updateCustomer(@Valid @ModelAttribute("customer") Customer upCustomer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "customers/edit";
        }
        custRepo.save(upCustomer);
        return "redirect:/customers";
    }
}
