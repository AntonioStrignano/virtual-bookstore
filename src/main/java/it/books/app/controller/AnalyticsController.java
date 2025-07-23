package it.books.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import it.books.app.model.Analytic;
import it.books.app.repository.AnalyticsRepository;

@Controller
@RequestMapping("/analytics")
public class AnalyticsController {

    @Autowired
    private AnalyticsRepository analyticsRepo;

    // ---- READ ----
    @GetMapping("")
    public String getAllAnalytics(Model model) {
        model.addAttribute("analytics", analyticsRepo.findAll());
        return "/analytics/analyt-home";
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
