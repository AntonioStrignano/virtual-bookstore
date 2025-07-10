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

import it.books.app.model.Book;
import it.books.app.repository.AuthorRepository;
import it.books.app.repository.AwardRepository;
import it.books.app.repository.BookRepository;
import it.books.app.repository.EditionRepository;
import it.books.app.repository.FormatRepository;
import it.books.app.repository.GenreRepository;
import it.books.app.repository.PublisherRepository;
import it.books.app.repository.TranslatorRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepo;

    @Autowired
    private AuthorRepository authorRepo;

    @Autowired
    private EditionRepository editionRepo;

    @Autowired
    private AwardRepository awardRepo;

    @Autowired
    private GenreRepository genreRepo;

    @Autowired
    private PublisherRepository publRepo;

    @Autowired
    private FormatRepository formatRepo;

    @Autowired
    private TranslatorRepository translRepo;

    // ---- READ ----
    // CATALOG
    @GetMapping("")
    public String bookCatalog(Model model) {

        model.addAttribute("books", bookRepo.findAll());

        return "/books/book-home";
    }

    // BOOK DETAIL
    @GetMapping("{id}")
    public String bookDetail(@PathVariable("id") Integer id, Model model) {

        model.addAttribute("book", bookRepo.getReferenceById(id));

        return "/detail";
    }

    // ---- CREATE ----
    // GET
    @GetMapping("/create")
    public String createNewBook(Model model) {

        addAllRepos(model);
        Book newBook = new Book();
        model.addAttribute("book", newBook);

        return "/books/edit";
    }

    // POST
    @PostMapping("/create")
    public String storeNewBook(@Valid @ModelAttribute("book") Book newBook, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "/books/edit";
        }
        bookRepo.save(newBook);

        return "redirect:/books";
    }

    // ---- UPDATE ----
    // GET
    @GetMapping("/edit/{id}")
    public String editBook(Model model, @PathVariable("id") Integer id) {

        addAllRepos(model);
        model.addAttribute("editMode", true);
        model.addAttribute("book", bookRepo.getReferenceById(id));

        return "/books/edit";
    }

    // POST
    @PostMapping("/{id}/update")
    public String updateBook(@Valid @ModelAttribute("book") Book updateBook, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "/books/edit";
        }

        bookRepo.save(updateBook);

        return "redirect:/books";
    }

    // ---- DELETE ----
    // POST
    @PostMapping("/{id}/delete")
    public String deleteBook(@PathVariable("id") Integer id) {

        bookRepo.deleteById(id);
        return "redirect:/books";
    }

    // ---- OTHERS ----
    private void addAllRepos(Model model) {

        model.addAttribute("books", bookRepo.findAll());
        model.addAttribute("authors", authorRepo.findAll());
        model.addAttribute("editions", editionRepo.findAll());
        model.addAttribute("allAwards", awardRepo.findAll());
        model.addAttribute("allGenres", genreRepo.findAll());
        model.addAttribute("publishers", publRepo.findAll());
        model.addAttribute("formats", formatRepo.findAll());
        model.addAttribute("translators", translRepo.findAll());
    }
}
