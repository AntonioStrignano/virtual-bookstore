package it.books.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import it.books.app.repository.BookstoreRoleRepository;
import it.books.app.repository.CartRepository;
import it.books.app.repository.CustomerRepository;
import it.books.app.repository.NotificationRepository;
import it.books.app.repository.RoleRepository;
import it.books.app.repository.SearchHistoryRepository;
import it.books.app.repository.ShopAssistantRepository;
import it.books.app.repository.UserRepository;
import it.books.app.repository.WarehouseLocationRepository;
import it.books.app.repository.WishlistRepository;

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
    private BookstoreRoleRepository bookstoreRoleRepo;

    @Autowired
    private WarehouseLocationRepository warehouseLocationRepo;

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

}
