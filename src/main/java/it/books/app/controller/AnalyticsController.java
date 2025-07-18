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

import it.books.app.model.Analytic;
import it.books.app.repository.AnalyticsRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/analytics")
public class AnalyticsController {

    @Autowired
    private AnalyticsRepository analyticsRepo;

    // ---- READ ----
    @GetMapping("")
    public String getAllAnalytics(Model model) {
        model.addAttribute("analytics", analyticsRepo.findAll());
        return "/analytic/home";
    }

    // ---- CREATE ----
    @GetMapping("/create")
    public String newAnalytic(Model model) {
        Analytic analytic = new Analytic();
        model.addAttribute("analytic", analytic);
        return "/analytic/edit";
    }

    @PostMapping("/create")
    public String createAnalytic(@Valid @ModelAttribute("analytic") Analytic analytic, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/analytic/edit";
        }

        analyticsRepo.save(analytic);
        return "redirect:/analytics";
    }

    // ---- UPDATE ----
    @GetMapping("/edit/{id}")
    public String editAnalytic(@PathVariable("id") Integer id, Model model) {
        Analytic analytic = analyticsRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid analytic ID: " + id));
        model.addAttribute("analytic", analytic);
        model.addAttribute("editMode", true);
        return "/analytic/edit";
    }

    @PostMapping("/update")
    public String updateAnalytic(@Valid @ModelAttribute("analytic") Analytic analytic, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/analytic/edit";
        }

        analyticsRepo.save(analytic);
        return "redirect:/analytics";
    }

    // ---- DELETE ----
    @GetMapping("/delete/{id}")
    public String deleteAnalytic(@PathVariable("id") Integer id) {
        Analytic analytic = analyticsRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid analytic ID: " + id));
        analyticsRepo.delete(analytic);
        return "redirect:/analytics";
    }

}
