package it.books.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.books.app.repository.CartRepository;
import it.books.app.repository.CustomerRepository;
import it.books.app.repository.InventoryRepository;

@Controller
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private InventoryRepository invRepo;

    @Autowired
    private CustomerRepository custRepo;

// ---- READ ----
    @GetMapping("{customerId}")
    public String cartPage(Model model, @PathVariable("customerId") Integer customerId) {
        model.addAttribute("cart", cartRepo.findByCustomerId(customerId));
        return "/cart/cart-home";
    }

    // ---- UPDATE ----
    @PostMapping("{invId}/add-cart/{custId}")
    public String addToCart(
            @PathVariable("invId") Integer invId,
            @PathVariable("custId") Integer custId) {
        cartRepo.getReferenceById(custRepo.getReferenceById(custId).getCartId().getId()).getInventoryList().add(invRepo.getReferenceById(invId));
        return "/inventory/" + invId;
    }

    @PostMapping("{invId}/remove-cart/{custId}")
    public String removeFromCart(
            @PathVariable("invId") Integer invId,
            @PathVariable("custId") Integer custId) {
        cartRepo.getReferenceById(custRepo.getReferenceById(custId).getCartId().getId()).getInventoryList().remove(invRepo.getReferenceById(invId));
        return "/inventory/" + invId;
    }

    @PostMapping("{custId}/clear-cart")
    public String clearCart(@PathVariable("custId") Integer custId) {
        cartRepo.getReferenceById(custRepo.getReferenceById(custId).getCartId().getId()).getInventoryList().clear();
        return "/carts/" + custId;
    }

}
