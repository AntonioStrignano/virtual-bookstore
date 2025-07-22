package it.books.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.books.app.model.BookCollection;
import it.books.app.repository.BookCollectionRepository;
import it.books.app.repository.BookRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/collections")
public class BookCollectionController {

    @Autowired
    private BookCollectionRepository bookCollRepo;

    @Autowired
    private BookRepository bookRepo;

    // ---- READ ----
    //get
    @GetMapping("")
    public String bookCollectionHome(Model model) {
        model.addAttribute("collections", bookCollRepo.findAll());
        return "/collections/collec-home";

    }
    // ---- CREATE ----
//get

    @GetMapping("/create")
    public String newBookCollection(Model model) {
        model.addAttribute("bookCollection", new BookCollection());
        model.addAttribute("books", bookRepo.findAll());

        return "/collections/edit";
    }
//post

    @PostMapping("/create")
    public String createBookCollection(@Valid @ModelAttribute("bookCollection") BookCollection bookCollection, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("books", bookRepo.findAll());
            return "/collections/edit";
        }
        bookCollRepo.save(bookCollection);
        return "redirect:/collections";
    }
    // ---- UPDATE ----
    // get

    @GetMapping("/edit/{id}")
    public String editBookCollection(@ModelAttribute("id") Integer id, Model model) {
        BookCollection bookCollection = bookCollRepo.findById(id).orElse(null);
        if (bookCollection == null) {
            return "redirect:/collections";
        }
        model.addAttribute("bookCollection", bookCollection);
        model.addAttribute("books", bookRepo.findAll());
        model.addAttribute("editMode", true);

        return "/collections/edit";
    }

    //post
    @PostMapping("/{id}/update")
    public String updateBookCollection(@Valid @ModelAttribute("bookCollection") BookCollection bookCollection, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("books", bookRepo.findAll());
            model.addAttribute("editMode", true);
            return "/collections/edit";
        }
        bookCollRepo.save(bookCollection);
        return "redirect:/collections";
    }

    // ---- DELETE ----
    // post
    @PostMapping("/{id}/delete")
    public String deleteBookCollection(@ModelAttribute("id") Integer id) {
        BookCollection bookCollection = bookCollRepo.findById(id).orElse(null);
        if (bookCollection != null) {
            bookCollRepo.delete(bookCollection);
        }
        return "redirect:/collections";
    }

}
