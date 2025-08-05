package it.books.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.books.app.model.Analytic;
import it.books.app.repository.AnalyticRepository;
import it.books.app.repository.AnalyticTypeRepository;
import it.books.app.repository.BookRepository;
import it.books.app.repository.CustomerRepository;
import it.books.app.repository.WishlistRepository;

@Controller
@RequestMapping("/wishlists")
public class WishlistController {

    @Autowired
    private WishlistRepository wishlistRepo;

    @Autowired
    private BookRepository bookRepo;

    @Autowired
    private CustomerRepository custRepo;

    @Autowired
    private AnalyticRepository analyticRepo;

    @Autowired
    private AnalyticTypeRepository analyticTypeRepo;

// ---- READ ----
    @GetMapping("{userId}")
    public String wishPage(Model model, @PathVariable("userId") Integer userId) {
        model.addAttribute("wishlist", custRepo.getReferenceById(userId).getWishlistId());
        return "/wishlist/wish-home";
    }

// ----     UPDATE   ----
    @PostMapping("{bookId}/add-wishlist/{custId}")
    public String addToWishlist(
            @PathVariable("bookId") Integer bookId,
            @PathVariable("custId") Integer custId) {
        wishlistRepo.getReferenceById(custRepo.getReferenceById(custId).getWishlistId().getId()).getWishlistedBooks().add(bookRepo.getReferenceById(bookId));
        analyticRepo.save(new Analytic(bookRepo.getReferenceById(bookId), custRepo.getReferenceById(custId), analyticTypeRepo.getReferenceById(6)));
        return "/books/" + bookId;
    }

    @PostMapping("{bookId}/remove-wishlist/{custId}")
    public String removeFromWishlist(
            @PathVariable("bookId") Integer bookId,
            @PathVariable("custId") Integer custId) {
        wishlistRepo.getReferenceById(custRepo.getReferenceById(custId).getWishlistId().getId()).getWishlistedBooks().remove(bookRepo.getReferenceById(bookId));
        analyticRepo.save(new Analytic(bookRepo.getReferenceById(bookId), custRepo.getReferenceById(custId), analyticTypeRepo.getReferenceById(17)));
        return "/books/" + bookId;
    }
}
