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

import it.books.app.model.AnalyticType;
import it.books.app.repository.AnalyticTypeRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/analytic-types")
public class AnalyticTypeController {

    @Autowired
    private AnalyticTypeRepository analyticTypeRepo;

    // ---- READ ----
    @GetMapping("")
    public String getAllAnalyticTypes(Model model) {
        model.addAttribute("analyticTypes", analyticTypeRepo.findAll());
        return "/analytic-type/home";
    }

    // ---- CREATE ----
    @GetMapping("/create")
    public String newAnalyticType(Model model) {
        AnalyticType analyticType = new AnalyticType();
        model.addAttribute("analyticType", analyticType);
        return "/analytic-type/edit";
    }

    @PostMapping("/create")
    public String createAnalyticType(@Valid @ModelAttribute("analyticType") AnalyticType analyticType, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/analytic-type/edit";
        }

        analyticTypeRepo.save(analyticType);
        return "redirect:/analytic-types";
    }
    // ---- UPDATE ----

    @GetMapping("/edit/{id}")
    public String editAnalyticType(@PathVariable("id") Integer id, Model model) {
        AnalyticType analyticType = analyticTypeRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid analytic type ID: " + id));
        model.addAttribute("analyticType", analyticType);
        model.addAttribute("editMode", true);
        return "/analytic-type/edit";
    }

    @PostMapping("/update")
    public String updateAnalyticType(@Valid @ModelAttribute("analyticType") AnalyticType analyticType, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/analytic-type/edit";
        }

        analyticTypeRepo.save(analyticType);
        return "redirect:/analytic-types";
    }
    // ---- DELETE ----

    @PostMapping("/delete/{id}")
    public String deleteAnalyticType(@PathVariable("id") Integer id) {
        AnalyticType analyticType = analyticTypeRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid analytic type ID: " + id));
        analyticTypeRepo.delete(analyticType);
        return "redirect:/analytic-types";
    }
}
