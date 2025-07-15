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

import it.books.app.model.ShopAssistant;
import it.books.app.repository.ShopAssistantRepository;
import it.books.app.repository.UserRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/shop-assistants")
public class ShopAssistantController {

    @Autowired
    private ShopAssistantRepository shopAssRepo;

    @Autowired
    private UserRepository userRepo;

    // ---- READ  ----
    @GetMapping("shop-ass")

    public String adminShopAssistantsView(Model model) {
        model.addAttribute("shopAssistants", shopAssRepo.findAll());

        return "/shop-ass/shop-ass-home";
    }

    // ---- CREATE ----
    @GetMapping("/new/{userId}")
    public String newShopAss(Model model, @PathVariable("userId") Integer userId) {

        ShopAssistant newShopAss = new ShopAssistant();
        userRepo.getReferenceById(userId).setSecondId(newShopAss.getId());
        model.addAttribute("shop-ass", newShopAss);
        return "/shop-ass/edit";
    }

    @PostMapping("/create")
    public String createShopAss(@Valid @ModelAttribute("shop-ass") ShopAssistant newShopAss, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/shop-ass/edit";
        }

        shopAssRepo.save(newShopAss);

        return "redirect:/shop-assistants";
    }

    // ---- UPDATE ----
    @GetMapping("/edit/{userId}")
    public String editShopAss(Model model, @PathVariable("userId") Integer userId) {
        model.addAttribute("editMode", true);
        model.addAttribute("shop-ass", shopAssRepo.getReferenceById(userRepo.getReferenceById(userId).getSecondId()));

        return "/shop-ass/edit";
    }

    @PostMapping("/{id}/update")
    public String updateShopAss(@Valid @ModelAttribute("shop-ass") ShopAssistant upShopAss, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/shop-ass/edit";
        }
        shopAssRepo.save(upShopAss);

        return "redirect:/shop-assistants";
    }
}
