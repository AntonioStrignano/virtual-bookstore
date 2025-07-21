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

import it.books.app.model.DiscountType;
import it.books.app.repository.DiscountTypeRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/discount-types")
public class DiscountTypeController {

    @Autowired
    private DiscountTypeRepository discTypeRepo;

    // ---- READ ----
    // GET
    @GetMapping("")
    public String discTypePage(Model model) {

        model.addAttribute("types", discTypeRepo.findAll());
        return "disc-types/type-home";
    }

    // ---- CREATE ----
    // GET
    @GetMapping("/create")
    public String createType(Model model) {

        DiscountType newType = new DiscountType();
        model.addAttribute("type", newType);
        return "disc-types/edit";
    }

    // POST
    @PostMapping("/create")
    public String createType(@Valid @ModelAttribute("type") DiscountType newType, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/disc-types/edit";
        }
        discTypeRepo.save(newType);
        return "redirect:/discount-types";
    }

    // ---- UPDATE ----
    // GET
    @GetMapping("/edit/{id}")
    public String editType(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("editMode", true);
        model.addAttribute("type", discTypeRepo.getReferenceById(id));
        return "/disc-types/edit";
    }

    // POST
    @PostMapping("{id}/update")
    public String updateType(@Valid @ModelAttribute("type") DiscountType upType, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "/disc-types/edit";
        }
        discTypeRepo.save(upType);
        return "redirect:/discount-types";
    }

    // ---- DELETE ----
    // POST
    @PostMapping("/{id}/delete")
    public String deleteType(@PathVariable("id") Integer id) {

        discTypeRepo.deleteById(id);
        return "redirect:/discount-types";
    }
}
