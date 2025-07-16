package it.books.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import it.books.app.model.Order;
import it.books.app.repository.OrderRepository;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepo;

    // ---- READ ----
    @GetMapping("")
    public String orderPage(Model model) {
        model.addAttribute("orders", orderRepo.findAll());
        return "/orders/order-home";
    }

    @GetMapping("{id}")
    public String orderDetail(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("order", orderRepo.getReferenceById(id));
        return "/orders/order-detail";
    }

    // -- CREATE ----
    @GetMapping("/create/{cartId}")
    public String createOrder(Model model, @PathVariable("cartId") Integer cartId) {
        Order newOrder = new Order();
        model.addAttribute("order", newOrder);

        return "/orders/edit";
    }

    // -- UPDATE ----
    // -- DELETE ----
}
