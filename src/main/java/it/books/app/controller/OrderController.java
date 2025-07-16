package it.books.app.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.books.app.model.Order;
import it.books.app.repository.CartRepository;
import it.books.app.repository.OrderRepository;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private CartRepository cartRepo;

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
    @GetMapping("/new/{cartId}")
    public String createOrder(Model model, @PathVariable("cartId") Integer cartId) {
        Order newOrder = new Order();
        model.addAttribute("order", newOrder);
        model.addAttribute("cart", cartRepo.getReferenceById(cartId));
        model.addAttribute("totalPrice", cartRepo.getReferenceById(cartId).getTotalPrice());

        return "/orders/edit";
    }

    @PostMapping("/create")
    public String saveOrder(@ModelAttribute("order") Order order, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/orders/edit";
        }
        orderRepo.save(order);
        return "redirect:/orders/" + order.getId();
    }

    // -- UPDATE ----
    @GetMapping("/edit/{id}")
    public String editOrder(Model model, @PathVariable("id") Integer id) {
        Order upOrder = orderRepo.getReferenceById(id);
        model.addAttribute("order", upOrder);
        model.addAttribute("totalPrice", upOrder.getTotalPrice());
        return "/orders/edit";
    }

    @PostMapping("/update/{id}/confirm")
    public String confirmOrder(@PathVariable("id") Integer id) {
        Order order = orderRepo.getReferenceById(id);
        order.setConfirmationDate(LocalDateTime.now());
        orderRepo.save(order);
        return "redirect:/orders/" + id;
    }

    @PostMapping("update/{id}/ship ")
    public String shipOrder(@PathVariable("id") Integer id) {
        Order order = orderRepo.getReferenceById(id);
        order.setShippingDate(LocalDateTime.now());
        orderRepo.save(order);
        return "redirect:/orders/" + id;
    }

    // -- DELETE ----
    @PostMapping("{id}/delete")
    public String deleteOrder(@PathVariable("id") Integer id) {
        orderRepo.deleteById(id);
        return "redirect:/orders";
    }
}
