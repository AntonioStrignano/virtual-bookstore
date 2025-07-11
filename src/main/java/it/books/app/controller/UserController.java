package it.books.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.books.app.model.Role;
import it.books.app.model.User;
import it.books.app.repository.RoleRepository;
import it.books.app.repository.UserRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    // ---- READ ----
    public String adminUsersView(Model model) {

        model.addAttribute("users", userRepo.findAll());
        return "/users/admin";
    }

    // ---- CREATE ----
    //  NEW SHOP ASSISTANT
    @GetMapping("/new/shop-assistant")
    public String newShopAssistant(Model model) {
        User newUser = new User();
        model.addAttribute("user", newUser);
        model.addAttribute("deployee", true);
        return "/users/edit";
    }

    @PostMapping("/create/shop-assistant")
    public String createShopAssistant(Model model, @Valid @ModelAttribute("user") User newUser, BindingResult bindingResult) {

        List<Role> roles = new ArrayList<Role>();
        roles.add(roleRepo.getReferenceById(2));
        newUser.setRoles(roles);

        if (bindingResult.hasErrors()) {
            return "/users/edit";
        }
        userRepo.save(newUser);
        model.addAttribute("user", newUser);

        return "redirect:/shop-assistant/new/";
    }

    //  NEW CUSTOMER
    // ---- UPDATE ----
    // ---- DELETE ----
}
