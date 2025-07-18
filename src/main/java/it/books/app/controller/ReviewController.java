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

import it.books.app.model.Review;
import it.books.app.repository.BookRepository;
import it.books.app.repository.CustomerRepository;
import it.books.app.repository.ReviewRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewRepository revRepo;

    @Autowired
    private CustomerRepository custRepo;

    @Autowired
    private BookRepository bookRepo;

    // ---- READ ----
    @GetMapping("")
    public String revPage(Model model) {
        model.addAttribute("reviews", revRepo.findAll());
        return "reviews/home-rev";
    }

    // ---- CREATE ----
    @GetMapping("/create/{custId}/{bookId}")
    public String createRevPage(Model model, @PathVariable("custId") Integer custId, @PathVariable("bookId") Integer bookId) {
        Review newRev = new Review();
        newRev.setCustomerId(custRepo.getReferenceById(custId));
        newRev.setBookId(bookRepo.getReferenceById(bookId));
        model.addAttribute("rev", newRev);

        return "reviews/edit";
    }

    @PostMapping("/create")
    public String createRev(@Valid @ModelAttribute("rev") Review rev, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "reviews/edit";
        }
        revRepo.save(rev);
        return "redirect:/books/" + rev.getBookId().getId() + "/detail";
    }

    // ---- UPDATE ----
    @GetMapping("/edit/{id}")
    public String editRevPage(Model model, @PathVariable("id") Integer id) {
        Review upRev = revRepo.getReferenceById(id);
        model.addAttribute("editMode", true);
        model.addAttribute("rev", upRev);
        return "reviews/edit";
    }

    @PostMapping("/update")
    public String updateRev(@Valid @ModelAttribute("rev") Review upRev, BindingResult binding) {
        if (binding.hasErrors()) {
            return "reviews/edit";
        }
        revRepo.save(upRev);
        return "redirect:/books/" + upRev.getBookId().getId() + "/detail";
    }

    // ---- DELETE ----
    @PostMapping("/delete/{id}")
    public String deleteRev(@PathVariable("id") Integer id) {
        Integer bookId = revRepo.getReferenceById(id).getBookId().getId();
        revRepo.deleteById(id);
        return "redirect:/books" + bookId + "/detail";
    }
}
