package it.books.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.books.app.model.Analytic;
import it.books.app.model.Customer;
import it.books.app.model.Notification;
import it.books.app.model.Order;
import it.books.app.model.Review;
import it.books.app.model.Role;
import it.books.app.model.SearchHistory;
import it.books.app.model.User;
import it.books.app.repository.CartRepository;
import it.books.app.repository.CustomerRepository;
import it.books.app.repository.NotificationRepository;
import it.books.app.repository.OrderRepository;
import it.books.app.repository.ReviewRepository;
import it.books.app.repository.RoleRepository;
import it.books.app.repository.SearchHistoryRepository;
import it.books.app.repository.ShopAssistantRepository;
import it.books.app.repository.UserRepository;
import it.books.app.repository.WishlistRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private ShopAssistantRepository shopAssRepo;

    @Autowired
    private CustomerRepository custRepo;

    @Autowired
    private NotificationRepository notifiRepo;

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private WishlistRepository wishRepo;

    @Autowired
    private SearchHistoryRepository searchRepo;

    @Autowired
    private ReviewRepository revRepo;

    @Autowired
    private OrderRepository orderRepo;

    // ---- READ  ----
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

        List<Role> roles = new ArrayList<>();
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
    @GetMapping("/new/customer")
    public String newCustomer(Model model) {
        User newUser = new User();
        model.addAttribute("user", newUser);
        model.addAttribute("customer", true);
        return "/users/edit";
    }

    @PostMapping("/create/customer")
    public String createCustomer(Model model, @Valid @ModelAttribute("user") User newUser, BindingResult bindingResult) {
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepo.getReferenceById(3));
        newUser.setRoles(roles);

        if (bindingResult.hasErrors()) {
            return "/users/edit";
        }
        userRepo.save(newUser);
        model.addAttribute("user", newUser);
        return "redirect:/customer/new";
    }

    // ---- UPDATE ----
    // UPDATE SHOP ASSISTANT
    @GetMapping("/edit/shop-assistant/{id}")
    public String editShopAssistant(Model model, @PathVariable("id") Integer id) {

        model.addAttribute("user", userRepo.getReferenceById(id));
        model.addAttribute("deployee", true);
        return "/users/edit";
    }

    @PostMapping("/update/shop-assistant/{id}")
    public String updateShopAssistant(Model model, @Valid @ModelAttribute("user") User upUser, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "/users/edit";
        }
        userRepo.save(upUser);
        return "redirect:/shop-assistant/edit/{id}";
    }

    // UPDATE CUSTOMER
    @GetMapping("/edit/customer/{id}")
    public String editCustomer(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("user", userRepo.getReferenceById(id));
        model.addAttribute("customer", true);

        return "users/edit";
    }

    @PostMapping("/update/customer/{id")
    public String updateCustomer(Model model, @Valid @ModelAttribute("user") User upUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/users/edit";
        }
        userRepo.save(upUser);
        return "/redirect:/customer/edit/{id}";
    }

    // ---- DELETE ----
    @PostMapping("delete/user/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        User delUser = userRepo.getReferenceById(id);
        if (delUser.getRoles().contains(roleRepo.getReferenceById(2))) {
            // SHOP ASSISTANT
            shopAssRepo.getReferenceById(delUser.getSecondId()).setIsFired(true);
            userRepo.delete(delUser);
        } else if (delUser.getRoles().contains(roleRepo.getReferenceById(3))) {
            // CUSTOMER
            Customer delCustomer = custRepo.getReferenceById(delUser.getSecondId());
            for (SearchHistory record : delCustomer.getSearchHistory()) {
                searchRepo.delete(record);
            }
            wishRepo.delete(delCustomer.getWishlistId());
            for (Analytic analytic : delCustomer.getAnalytics()) {
                analytic.setCustomerId(null);
            }
            for (Notification notification : delCustomer.getNotifications()) {
                notifiRepo.delete(notification);
            }
            cartRepo.delete(delCustomer.getCartId());
            for (Review review : delCustomer.getReviews()) {
                review.setCustomerId(null);
            }
            for (Order order : delCustomer.getOrders()) {
                order.setCustomerId(null);
            }
            custRepo.delete(delCustomer);
            userRepo.delete(delUser);
        }

        return "redirect:/users";
    }

}
