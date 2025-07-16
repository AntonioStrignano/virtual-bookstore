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

import it.books.app.model.Inventory;
import it.books.app.repository.BookRepository;
import it.books.app.repository.DiscountRepository;
import it.books.app.repository.InventoryRepository;
import it.books.app.repository.InventoryStatusRepository;
import it.books.app.repository.WarehouseLocationRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/inventory")
public class InventoryController {

    // ---- WIRING ----
    @Autowired
    private InventoryRepository invRepo;

    @Autowired
    private InventoryStatusRepository invStatRepo;

    @Autowired
    private DiscountRepository discRepo;

    @Autowired
    private WarehouseLocationRepository locationRepo;

    @Autowired
    private BookRepository bookRepo;

    // ---- READ ----
    // GET
    @GetMapping("")
    public String inventoryPage(Model model) {

        model.addAttribute("books", bookRepo.findAll());
        model.addAttribute("stock", invRepo.findAll());
        return "/inventory/inv-home";
    }

    @GetMapping("{id}")
    public String inventoryDetail(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("article", invRepo.getReferenceById(id));
        return "/inventory/inv-detail";
    }

    // ---- CREATE ----
    // GET
    @GetMapping("/create")
    public String selectIsbn(Model model) {
        addAll(model);
        Inventory newArticle = new Inventory();
        model.addAttribute("article", newArticle);

        return "/inventory/edit";
    }

    // POST
    @PostMapping("/create")
    public String storeNewArticle(@Valid @ModelAttribute("article") Inventory newArticle, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "/inventory/edit";
        }
        invRepo.save(newArticle);

        return "redirect:/inventory";
    }

    // ---- UPDATE ----
    // GET
    @GetMapping("/edit/{id}")
    public String editArticle(Model model, @PathVariable("id") Integer id) {

        addAll(model);
        model.addAttribute("editMode", true);
        model.addAttribute("article", invRepo.getReferenceById(id));

        return "/inventory/edit";
    }

    // POST
    @PostMapping("{id}/update")
    public String updateArticle(@Valid @ModelAttribute("article") Inventory upArticle, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "/inventory/edit";
        }
        invRepo.save(upArticle);

        return "redirect:/inventory";
    }

    // ---- DELETE ----
    // POST
    @PostMapping("{id}/delete")
    public String deleteArticle(@PathVariable("id") Integer id) {

        invRepo.deleteById(id);
        return "redirect:/inventory";
    }

    // ---- OTHERS ----
    private void addAll(Model model) {
        model.addAttribute("books", bookRepo.findAll());
        model.addAttribute("stati", invStatRepo.findAll());
        model.addAttribute("discs", discRepo.findAll());
        model.addAttribute("warehouseLocations", locationRepo.findAll());
    }
}
