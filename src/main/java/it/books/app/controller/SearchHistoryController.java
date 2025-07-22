package it.books.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.books.app.repository.SearchHistoryRepository;

@Controller
@RequestMapping("/search-history")
public class SearchHistoryController {

    @Autowired
    private SearchHistoryRepository searchHistRepo;

    // ---- READ ----
    //get
    @GetMapping("")
    public String searchHistoryPage(Model model) {
        model.addAttribute("searchHistory", searchHistRepo.findAll());
        return "/search-history/search-home";

    }

    // ---- DELETE ----
    // post
    @PostMapping("/{id}/delete")
    public String deleteSearchHistory(@PathVariable("id") Integer id) {
        searchHistRepo.deleteById(id);
        return "redirect:/search-history";
    }
}
