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

import it.books.app.model.Analytic;
import it.books.app.model.Customer;
import it.books.app.model.Notification;
import it.books.app.model.Order;
import it.books.app.model.Review;
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
    // GET
    @GetMapping("/new/shop-assistant")
    public String newUser(Model model) {
        User newUser = new User();
        newUser.getRoles().add(roleRepo.getReferenceById(2));
        model.addAttribute("user", newUser);
        return "/users/edit";
    }

    //  NEW CUSTOMER
    @GetMapping("/new/customer")
    public String newCustomer(Model model) {
        User newUser = new User();
        newUser.getRoles().add(roleRepo.getReferenceById(3));
        model.addAttribute("user", newUser);
        return "/users/edit";
    }

    // POST
    @PostMapping("/create")
    public String createUser(Model model, @Valid @ModelAttribute("user") User newUser, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "/users/edit";
        }

        userRepo.save(newUser);
        if (newUser.getRoles().contains(roleRepo.getReferenceById(2))) {
            // SHOP ASSISTANT
            return "redirect:/shop-assistant/create/" + newUser.getId();

        } else if (newUser.getRoles().contains(roleRepo.getReferenceById(3))) {
            // CUSTOMER
            return "redirect:/customer/create/" + newUser.getId();
        }

        return "redirect:/users";
    }

    // ---- UPDATE ----
    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable("id") Integer id, Model model) {
        User editUser = userRepo.getReferenceById(id);
        model.addAttribute("user", editUser);
        return "/users/edit";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid @ModelAttribute("user") User upUser, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "/users/edit";
        }
        userRepo.save(upUser);

        if (upUser.getRoles().contains(roleRepo.getReferenceById(2))) {
            // SHOP ASSISTANT
            return "redirect:/shop-assistant/edit/" + upUser.getId();

        } else if (upUser.getRoles().contains(roleRepo.getReferenceById(3))) {
            // CUSTOMER
            return "redirect:/customer/edit/" + upUser.getId();
        }

        return "redirect:/users";
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
