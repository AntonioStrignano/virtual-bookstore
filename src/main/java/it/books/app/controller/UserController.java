package it.books.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.books.app.dto.UserCustomerDto;
import it.books.app.dto.UserShopAssistantDto;
import it.books.app.model.BookstoreRole;
import it.books.app.model.Customer;
import it.books.app.model.Role;
import it.books.app.model.ShopAssistant;
import it.books.app.model.User;
import it.books.app.model.WarehouseLocation;
import it.books.app.model.Review;
import it.books.app.model.Order;
import it.books.app.repository.*;

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
    private BookstoreRoleRepository bookstoreRoleRepo;

    @Autowired
    private WarehouseLocationRepository warehouseLocationRepo;

    @Autowired
    private WishlistRepository wishRepo;

    @Autowired
    public CartRepository cartRepo;

    @Autowired
    public AnalyticRepository analyticRepo;

    @Autowired
    public SearchHistoryRepository searchHistoryRepo;

    //notific
    @Autowired
    public NotificationRepository notifRepo;

    //reviews
    @Autowired
    public ReviewRepository revRepo;

    //orders
    @Autowired
    public OrderRepository orderRepo;

    // ---- READ  ----
    public String adminUsersView(Model model) {
        model.addAttribute("users", userRepo.findAll());
        return "/users/admin";
    }

    // ---- CREATE SHOP ASSISTANT ----
    @GetMapping("/new-shop-assistant")
    public String newShopAssistant(Model model) {
        model.addAttribute("user", new UserShopAssistantDto());
        model.addAttribute("editMode", false);
        model.addAttribute("shopass", true);
        return "users/edit";
    }

    @PostMapping("/new-shop-assistant")
    public String createShopAssistant(@ModelAttribute UserShopAssistantDto shopAssDto) {
        // Recupera oggetti necessari dai repository
        List<Role> roles = roleRepo.findAllById(shopAssDto.getRoleIds());
        BookstoreRole bookstoreRole = bookstoreRoleRepo.findById(shopAssDto.getBookstoreRoleId()).orElse(null);;
        WarehouseLocation warehouseLocation = warehouseLocationRepo.findById(shopAssDto.getWarehouseLocationId()).orElse(null);

        // Crea ShopAssistant direttamente dal DTO e warehouseLocation
        ShopAssistant shopAssistant = new ShopAssistant(shopAssDto, warehouseLocation);
        shopAssRepo.save(shopAssistant);

        // Crea User direttamente dal DTO
        User user = new User(shopAssDto, roles, bookstoreRole, shopAssistant.getId());
        userRepo.save(user);

        return "redirect:/users/admin";
    }

    // ---- CREATE CUSTOMER ----
    @GetMapping("/new-customer")
    public String newCustomer(Model model) {
        model.addAttribute("user", new UserCustomerDto());
        model.addAttribute("editMode", false);
        model.addAttribute("customer", true);
        return "users/edit";
    }

    @PostMapping("/new-customer")
    public String createCustomer(@ModelAttribute UserCustomerDto customerDto) {
        // Recupera ruoli e bookstoreRole
        List<Role> roles = roleRepo.findAllById(customerDto.getRoles());
        BookstoreRole bookstoreRole = bookstoreRoleRepo.findById(customerDto.getBookstoreRole()).orElse(null);

        // Crea Customer direttamente dal DTO (crea anche Cart e Wishlist internamente)
        Customer customer = new Customer(customerDto);
        custRepo.save(customer);

        // Crea User direttamente dal DTO
        User user = new User(customerDto, roles, bookstoreRole, customer.getId());
        userRepo.save(user);

        return "redirect:/users/admin";
    }

// ---- EDIT    ----
    @GetMapping("/edit/{id}")
    public String editShopAssistant(Model model, @PathVariable("id") Integer id) {
        User user = userRepo.findById(id).orElse(null);
        if (user == null) {
            return "redirect:/users/admin"; // Redirect if user not found
        }
        ShopAssistant shopAssistant = shopAssRepo.findById(user.getSecondId()).orElse(null);
        if (shopAssistant == null) {
            return "redirect:/users/admin"; // Redirect if shop assistant not found
        }
        model.addAttribute("user", new UserShopAssistantDto(user, shopAssistant));
        model.addAttribute("editMode", true);
        model.addAttribute("shopass", true);
        return "users/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateShopAssistant(Model model, @ModelAttribute("user") UserShopAssistantDto shopAssDto, @PathVariable("id") Integer id) {
        User user = userRepo.findById(id).orElse(null);
        if (user == null) {
            return "redirect:/users/admin"; // Redirect if user not found
        }
        ShopAssistant shopAssistant = shopAssRepo.findById(user.getSecondId()).orElse(null);
        if (shopAssistant == null) {
            return "redirect:/users/admin"; // Redirect if shop assistant not found
        }

        // Update ShopAssistant and User
        shopAssistant.updateFromDto(shopAssDto);
        shopAssRepo.save(shopAssistant);

        user.updateFromDto(shopAssDto);
        userRepo.save(user);

        return "redirect:/users/admin";
    }

    // --- Delete ----
    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
        User user = userRepo.findById(id).orElse(null);
        if (user != null) {
            if (user.getSecondId() == 1) {
                model.addAttribute("errorMessage", "Non puoi cancellare questo user perché serve alle impostazioni.");
                return "users/admin";
            } else {
                if (user.getBookstoreRoleId() == 1) {
                    shopAssRepo.getReferenceById(user.getSecondId()).setIsFired(true);
                } else if (user.getBookstoreRoleId() == 2) {
                    wishRepo.deleteByCustomerId(user.getSecondId());
                    cartRepo.deleteByCustomerId(user.getSecondId());
                    analyticRepo.deleteByCustomerId(user.getSecondId());
                    searchHistoryRepo.deleteByCustomerId(user.getSecondId());
                    notifRepo.deleteByCustomerId(user.getSecondId());
                    List<Review> revs = revRepo.findByCustomerId(user.getSecondId());
                    for (Review rev : revs) {
                        rev.setCustomerId(custRepo.getReferenceById(1));
                        revRepo.save(rev);
                    }
                    List<Order> orders = orderRepo.findByCustomerId(user.getSecondId());
                    for (Order order : orders) {
                        order.setCustomerId(custRepo.getReferenceById(1));
                        orderRepo.save(order);
                    }
                    custRepo.deleteById(user.getSecondId());
                }
                userRepo.delete(user);
            }
        }
        return "redirect:/users/admin";
    }
}
