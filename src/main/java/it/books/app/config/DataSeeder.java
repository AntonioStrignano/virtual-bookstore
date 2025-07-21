package it.books.app.config;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import it.books.app.model.AnalyticType;
import it.books.app.model.Author;
import it.books.app.model.Award;
import it.books.app.model.Book;
import it.books.app.model.BookCollection;
import it.books.app.model.Discount;
import it.books.app.model.DiscountType;
import it.books.app.model.Edition;
import it.books.app.model.Format;
import it.books.app.model.Genre;
import it.books.app.model.Inventory;
import it.books.app.model.InventoryStatus;
import it.books.app.model.NotificationType;
import it.books.app.model.Publisher;
import it.books.app.model.Role;
import it.books.app.model.ShopAssistant;
import it.books.app.model.Translator;
import it.books.app.model.User;
import it.books.app.model.WarehouseLocation;
import it.books.app.repository.AnalyticTypeRepository;
import it.books.app.repository.AuthorRepository;
import it.books.app.repository.AwardRepository;
import it.books.app.repository.BookCollectionRepository;
import it.books.app.repository.BookRepository;
import it.books.app.repository.CartRepository;
import it.books.app.repository.CustomerRepository;
import it.books.app.repository.DiscountRepository;
import it.books.app.repository.DiscountTypeRepository;
import it.books.app.repository.EditionRepository;
import it.books.app.repository.FormatRepository;
import it.books.app.repository.GenreRepository;
import it.books.app.repository.InventoryRepository;
import it.books.app.repository.InventoryStatusRepository;
import it.books.app.repository.NotificationRepository;
import it.books.app.repository.NotificationTypeRepository;
import it.books.app.repository.OrderRepository;
import it.books.app.repository.PublisherRepository;
import it.books.app.repository.ReviewRepository;
import it.books.app.repository.RoleRepository;
import it.books.app.repository.SearchHistoryRepository;
import it.books.app.repository.ShopAssistantRepository;
import it.books.app.repository.TranslatorRepository;
import it.books.app.repository.UserRepository;
import it.books.app.repository.WarehouseLocationRepository;
import it.books.app.repository.WishlistRepository;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private WarehouseLocationRepository warehouseLocationRepo;

    @Autowired
    private NotificationTypeRepository notificationTypeRepo;

    @Autowired
    private InventoryStatusRepository inventoryStatusRepo;

    @Autowired
    private GenreRepository genreRepo;

    @Autowired
    private FormatRepository formatRepo;

    @Autowired
    private AnalyticTypeRepository analyticTypeRepo;

    @Autowired
    private DiscountTypeRepository discTypeRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private AuthorRepository authorRepo;

    @Autowired
    private AwardRepository awardRepo;

    @Autowired
    private TranslatorRepository translatorRepo;

    @Autowired
    private BookCollectionRepository bookCollectionRepo;

    @Autowired
    private PublisherRepository publisherRepo;

    @Autowired
    private EditionRepository editionRepo;

    @Autowired
    private BookRepository bookRepo;

    @Autowired
    private InventoryRepository inventoryRepo;

    @Autowired
    private DiscountRepository discountRepo;

    @Autowired
    private ShopAssistantRepository shopAssRepo;

    @Autowired
    private CustomerRepository custRepo;

    @Autowired
    private ReviewRepository reviewRepo;

    @Autowired
    private WishlistRepository wishRepo;

    @Autowired
    private NotificationRepository notifiRepo;

    @Autowired
    private SearchHistoryRepository searchRepo;

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private OrderRepository orderRepo;

    @Override
    public void run(String... args) throws Exception {

        // DEFAULT RECORDS
        // WAREHOSE LOCATION
        if (warehouseLocationRepo.count() == 0) {
            warehouseLocationRepo.save(new WarehouseLocation("Main Warehouse"));
            warehouseLocationRepo.save(new WarehouseLocation("Secondary Warehouse"));
        }

        // NOTIFICATION TYPES
        if (notificationTypeRepo.count() == 0) {
            notificationTypeRepo.save(new NotificationType("Order Confirmation", "Your order has been confirmed!"));
            notificationTypeRepo.save(new NotificationType("Shipping Update", "Your order has been shipped!"));
            notificationTypeRepo.save(new NotificationType("Delivery Confirmation", "Your order has been delivered!"));
            notificationTypeRepo.save(new NotificationType("Promotional Offer", "Check out our latest offers!"));
            notificationTypeRepo.save(new NotificationType("Feedback Request", "We value your feedback!"));
            notificationTypeRepo.save(new NotificationType("System Maintenance", "Scheduled maintenance will occur on [date]."));
            notificationTypeRepo.save(new NotificationType("Order Cancellation", "Your order has been cancelled."));
            notificationTypeRepo.save(new NotificationType("Wishlist Reminder", "Items in your wishlist are on sale!"));
        }

        // INVENTORY STATUS
        if (inventoryStatusRepo.count() == 0) {
            inventoryStatusRepo.save(new InventoryStatus("Available", "Item is available for sale"));
            inventoryStatusRepo.save(new InventoryStatus("Out of Stock", "Item is currently out of stock"));
            inventoryStatusRepo.save(new InventoryStatus("Discontinued", "Item is no longer available"));
        }

        // GENRES
        if (genreRepo.count() == 0) {
            genreRepo.save(new Genre("Unknown"));
            genreRepo.save(new Genre("Fiction"));
            genreRepo.save(new Genre("Non-Fiction"));
            genreRepo.save(new Genre("Science Fiction"));
            genreRepo.save(new Genre("Fantasy"));
            genreRepo.save(new Genre("Mystery"));
            genreRepo.save(new Genre("Biography"));
        }

        // FORMATS
        if (formatRepo.count() == 0) {
            formatRepo.save(new Format("Unknown", false));
            formatRepo.save(new Format("Paperback", false));
            formatRepo.save(new Format("Hardcover", false));
            formatRepo.save(new Format("Ebook", true));
            formatRepo.save(new Format("Audiobook", true));
            formatRepo.save(new Format("Digital", true));
            formatRepo.save(new Format("Magazine", false));
            formatRepo.save(new Format("Journal", false));
            formatRepo.save(new Format("Comic Book", false));
            formatRepo.save(new Format("Graphic Novel", false));
            formatRepo.save(new Format("Textbook", false));
            formatRepo.save(new Format("Reference", false));
            formatRepo.save(new Format("Manual", false));
            formatRepo.save(new Format("Guide", false));
            formatRepo.save(new Format("Workbook", false));

        }

        // ANALYTIC TYPES
        if (analyticTypeRepo.count() == 0) {
            analyticTypeRepo.save(new AnalyticType("Book Views", "searched for book)"));
            analyticTypeRepo.save(new AnalyticType("Book Purchases", "purchased book"));
            analyticTypeRepo.save(new AnalyticType("User Registrations", "registered on the platform"));
            analyticTypeRepo.save(new AnalyticType("User Logins", "logged into the platform"));
            analyticTypeRepo.save(new AnalyticType("Cart Abandonments", "abandoned cart"));
            analyticTypeRepo.save(new AnalyticType("Wishlist Additions", "added item to wishlist"));
            analyticTypeRepo.save(new AnalyticType("Reviews Posted", "posted a review"));
            analyticTypeRepo.save(new AnalyticType("Ratings Given", "gave a rating"));
            analyticTypeRepo.save(new AnalyticType("Notifications Sent", "sent a notification"));
            analyticTypeRepo.save(new AnalyticType("Notifications Clicked", "clicked on a notification"));
            analyticTypeRepo.save(new AnalyticType("Notifications Dismissed", "dismissed a notification"));
            analyticTypeRepo.save(new AnalyticType("User Feedback", "provided feedback"));
            analyticTypeRepo.save(new AnalyticType("User Preferences", "updated user preferences"));
            analyticTypeRepo.save(new AnalyticType("Search Queries", "performed a search query"));
            analyticTypeRepo.save(new AnalyticType("Product Recommendations", "viewed product recommendations"));
            analyticTypeRepo.save(new AnalyticType("Promotional Campaigns", "interacted with a promotional campaign"));
            analyticTypeRepo.save(new AnalyticType("Wishlist Removals", "removed item from wishlist"));
            analyticTypeRepo.save(new AnalyticType("Book Downloads", "downloaded a digital book"));
            analyticTypeRepo.save(new AnalyticType("Account Deletions", "deleted user account"));
        }

        // DISCOUNT TYPES
        if (discTypeRepo.count() == 0) {
            discTypeRepo.save(new DiscountType("Percentage", "A percentage discount on the total price"));
            discTypeRepo.save(new DiscountType("Fixed Amount", "A fixed amount discount on the total price"));
            discTypeRepo.save(new DiscountType("Sub Price", "A discount applied to a specific sub-price of an item"));
        }

        //ROLES
        if (roleRepo.count() == 0) {
            roleRepo.save(new Role("ADMIN"));
            roleRepo.save(new Role("SHOP_ASSISTANT"));
            roleRepo.save(new Role("CUSTOMER"));
        }

        // USERS
        if (userRepo.count() == 0) {
            User admin = new User("admin", "admin123");
            List<Role> adminRoles = new ArrayList<>();
            adminRoles.add(roleRepo.getReferenceById(1));
            admin.setRoles(adminRoles);
            userRepo.save(admin);
        }

        // TEST RECORDS
        //author
        if (authorRepo.count() == 0) {
            authorRepo.save(new Author("Unknown", "Author", LocalDate.of(1900, 1, 1), "Unknown", "No bio available", "none", "none", "none", true, genreRepo.getReferenceById(1)));
            authorRepo.save(new Author("John", "Doe", LocalDate.of(1980, 1, 1), "American", "Author bio", "http://johndoe.com", "http://johndoe.com/profile.jpg", "http://johndoe.com/social", true, genreRepo.getReferenceById(1)));
            authorRepo.save(new Author("Jane", "Smith", LocalDate.of(1990, 2, 2), LocalDate.of(2020, 3, 3), "British", "Author bio", "http://janesmith.com", "http://janesmith.com/profile.jpg", "http://janesmith.com/social", false, genreRepo.getReferenceById(2)));
            authorRepo.save(new Author("Alice", "Johnson", LocalDate.of(1975, 4, 4), "Canadian", "Author bio", "http://alicejohnson.com", "http://alicejohnson.com/profile.jpg", "http://alicejohnson.com/social", true, genreRepo.getReferenceById(3)));
            authorRepo.save(new Author("Bob", "Brown", LocalDate.of(1985, 5, 5), LocalDate.of(2015, 6, 6), "Australian", "Author bio", "http://bobbrown.com", "http://bobbrown.com/profile.jpg", "http://bobbrown.com/social", false, genreRepo.getReferenceById(4)));
            authorRepo.save(new Author("Charlie", "Davis", LocalDate.of(2000, 7, 7), "Indian", "Author bio", "http://charliedavis.com", "http://charliedavis.com/profile.jpg", "http://charliedavis.com/social", true, genreRepo.getReferenceById(5)));

        }
        //award
        if (awardRepo.count() == 0) {
            awardRepo.save(new Award("Best Fiction", 2021, "Award for the best fiction book", "Literary"));
            awardRepo.save(new Award("Best Non-Fiction", 2020, "Award for the best non-fiction book", "Literary"));
            awardRepo.save(new Award("Best Science Fiction", 2019, "Award for the best science fiction book", "Genre"));
            awardRepo.save(new Award("Best Fantasy", 2018, "Award for the best fantasy book", "Genre"));
            awardRepo.save(new Award("Best Mystery", 2017, "Award for the best mystery book", "Genre"));
        }
        //translator
        if (translatorRepo.count() == 0) {
            translatorRepo.save(new Translator("Unknown", "Translator", LocalDate.of(1900, 1, 1), "Unknown", "No bio available", "none", "none", "none", true));
            translatorRepo.save(new Translator("Maria", "Garcia", LocalDate.of(1985, 8, 8), "Spanish", "Translator bio", "http://mariagarcia.com", "http://mariagarcia.com/profile.jpg", "http://mariagarcia.com/social", true));
            translatorRepo.save(new Translator("Liu", "Wang", LocalDate.of(1992, 9, 9), LocalDate.of(2021, 10, 10), "Chinese", "Translator bio", "http://liuwang.com", "http://liuwang.com/profile.jpg", "http://liuwang.com/social"));
            translatorRepo.save(new Translator("Ahmed", "Khan", LocalDate.of(1988, 11, 11), "Pakistani", "Translator bio", "http://ahmedkhan.com", "http://ahmedkhan.com/profile.jpg", "http://ahmedkhan.com/social", true));
            translatorRepo.save(new Translator("Sofia", "Martinez", LocalDate.of(1995, 12, 12), LocalDate.of(2022, 1, 1), "Mexican", "Translator bio", "http://sofiamartinez.com", "http://sofiamartinez.com/profile.jpg", "http://sofiamartinez.com/social"));
            translatorRepo.save(new Translator("John", "Smith", LocalDate.of(1980, 2, 2), "American", "Translator bio", "http://johnsmith.com", "http://johnsmith.com/profile.jpg", "http://johnsmith.com/social", false));
        }
        //publisher
        if (publisherRepo.count() == 0) {
            publisherRepo.save(new Publisher("Unknown Publisher", 1900, "Unknown", "Unknown", "none", "generic email", "none", "No description available"));
            publisherRepo.save(new Publisher("Penguin Random House", 1927, "USA", "New York", "http://penguinrandomhouse.com", "generic email", "123 Penguin St, NY", "Leading publisher of books worldwide"));
            publisherRepo.save(new Publisher("HarperCollins", 1817, "USA", "New York", "http://harpercollins.com", "generic email", "456 Harper St, NY", "Global publisher with a diverse catalog"));
            publisherRepo.save(new Publisher("Macmillan", 1843, "UK", "London", "http://macmillan.com", "generic email", "789 Macmillan St, London", "Publisher known for quality literature"));
            publisherRepo.save(new Publisher("Simon & Schuster", 1924, "USA", "New York", "http://simonandschuster.com", "generic email", "101 Simon St, NY", "Publisher with a wide range of genres"));
            publisherRepo.save(new Publisher("Hachette Livre", 1826, "France", "Paris", "http://hachette.com", "generic email", "202 Hachette St, Paris", "Leading French publisher with global reach"));
        }

        //edition
        if (editionRepo.count() == 0) {
            editionRepo.save(new Edition("Unknown Edition"));
            editionRepo.save(new Edition("First Edition"));
            editionRepo.save(new Edition("Second Edition"));
            editionRepo.save(new Edition("Revised Edition"));
            editionRepo.save(new Edition("Collector's Edition"));
            editionRepo.save(new Edition("Limited Edition"));
            editionRepo.save(new Edition("Anniversary Edition"));
            editionRepo.save(new Edition("Special Edition"));
        }

        //book
        if (bookRepo.count() == 0) {
            bookRepo.save(new Book("Unknown Book", authorRepo.getReferenceById(1), "0000000000000",
                    List.of(genreRepo.getReferenceById(1)), "Unknown", LocalDate.of(1900, 1, 1),
                    publisherRepo.getReferenceById(1), "No description available", "none",
                    formatRepo.getReferenceById(1), 100, "none", "All rights reserved", editionRepo.getReferenceById(1)));
            bookRepo.save(new Book("The Great Gatsby", authorRepo.getReferenceById(2), "9780743273565",
                    List.of(genreRepo.getReferenceById(2)), "English", LocalDate.of(1925, 4, 10),
                    publisherRepo.getReferenceById(2), "A classic novel set in the Jazz Age",
                    "http://example.com/greatgatsby.jpg", formatRepo.getReferenceById(2), 180, "8.5 x 5.5 inches", "Copyright © 1925", editionRepo.getReferenceById(2)));
            bookRepo.save(new Book("To Kill a Mockingbird", authorRepo.getReferenceById(3), "9780061120084",
                    List.of(genreRepo.getReferenceById(3)), "English", LocalDate.of(1960, 7, 11),
                    publisherRepo.getReferenceById(3), "A novel about racial injustice in the Deep South",
                    "http://example.com/tokillamockingbird.jpg", formatRepo.getReferenceById(3), 281, "8 x 5.25 inches", "Copyright © 1960", editionRepo.getReferenceById(3)));
            bookRepo.save(new Book("1984", authorRepo.getReferenceById(4), "9780451524935",
                    List.of(genreRepo.getReferenceById(4)), "English", LocalDate.of(1949, 6, 8),
                    publisherRepo.getReferenceById(4), "A dystopian novel about totalitarianism",
                    "http://example.com/1984.jpg", formatRepo.getReferenceById(4), 328, "8 x 5.31 inches", "Copyright © 1949", editionRepo.getReferenceById(4)));
            bookRepo.save(new Book("Pride and Prejudice", authorRepo.getReferenceById(5), "9780141040349", List.of(awardRepo.getReferenceById(1)),
                    List.of(genreRepo.getReferenceById(5)),
                    "English", LocalDate.of(1813, 1, 28), publisherRepo.getReferenceById(5),
                    "A romantic novel about manners and marriage", "http://example.com/prideandprejudice.jpg",
                    formatRepo.getReferenceById(5), 279, "7.8 x 5.1 inches", "Copyright © 1813", translatorRepo.getReferenceById(3), editionRepo.getReferenceById(5)));

            bookRepo.save(new Book("The Catcher in the Rye", authorRepo.getReferenceById(6), "9780316769488",
                    List.of(awardRepo.getReferenceById(2)),
                    List.of(genreRepo.getReferenceById(6)), "English", LocalDate.of(1951, 7, 16),
                    publisherRepo.getReferenceById(6), "A novel about teenage angst and alienation",
                    "http://example.com/catcherintherye.jpg", formatRepo.getReferenceById(6), 277, "8 x 5.31 inches", "Copyright © 1951", editionRepo.getReferenceById(6)));
        }
        //book collection (setted)

        if (bookCollectionRepo.count() == 0) {
            bookCollectionRepo.save(new BookCollection("Classic Literature", "A collection of classic literary works", true, List.of(bookRepo.getReferenceById(2), bookRepo.getReferenceById(3)), publisherRepo.getReferenceById(2)));
            bookCollectionRepo.save(new BookCollection("Science Fiction Classics", "A collection of classic science fiction novels", false, List.of(bookRepo.getReferenceById(4), bookRepo.getReferenceById(5)), publisherRepo.getReferenceById(3)));
            bookCollectionRepo.save(new BookCollection("Mystery and Thriller Collection", "A collection of mystery and thriller novels", true, List.of(bookRepo.getReferenceById(6)), publisherRepo.getReferenceById(4)));
            bookCollectionRepo.save(new BookCollection("Fantasy Favorites", "A collection of popular fantasy novels", false, List.of(bookRepo.getReferenceById(1)), publisherRepo.getReferenceById(5)));
        }
        //discount

        if (discountRepo.count() == 0) {
            discountRepo.save(new Discount("Spring Sale", LocalDateTime.of(2023, 3, 1, 0, 0), LocalDateTime.of(2023, 5, 31, 23, 59), 20.0, discTypeRepo.getReferenceById(1)));
            discountRepo.save(new Discount("Summer Clearance", LocalDateTime.of(2023, 6, 1, 0, 0), LocalDateTime.of(2023, 8, 31, 23, 59), 15.0, discTypeRepo.getReferenceById(2)));
            discountRepo.save(new Discount("Black Friday Deal", LocalDateTime.of(2023, 11, 24, 0, 0), LocalDateTime.of(2023, 11, 30, 23, 59), 30.0, discTypeRepo.getReferenceById(3)));

        }
        //inventory

        if (inventoryRepo.count() == 0) {
            inventoryRepo.save(new Inventory(
                    19.99, 100, "First edition in mint condition",
                    warehouseLocationRepo.getReferenceById(1),
                    inventoryStatusRepo.getReferenceById(1),
                    List.of(discountRepo.getReferenceById(1)),
                    bookRepo.getReferenceById(1)));

            inventoryRepo.save(new Inventory(
                    15.99, 50, "Paperback edition with minor wear", warehouseLocationRepo.getReferenceById(2),
                    inventoryStatusRepo.getReferenceById(1), List.of(), bookRepo.getReferenceById(2)));
            inventoryRepo.save(new Inventory(
                    12.99, 75, "Ebook edition available for download", warehouseLocationRepo.getReferenceById(1),
                    inventoryStatusRepo.getReferenceById(1), List.of(discountRepo.getReferenceById(2)),
                    bookRepo.getReferenceById(3)));
            inventoryRepo.save(new Inventory(
                    9.99, 200, "Audiobook edition narrated by a famous actor", warehouseLocationRepo.getReferenceById(2),
                    inventoryStatusRepo.getReferenceById(1), List.of(discountRepo.getReferenceById(3)),
                    bookRepo.getReferenceById(4)));

        }
        //shop assistant

        if (shopAssRepo.count() == 0) {
            shopAssRepo.save(new ShopAssistant("Jon", "Doe", "personal email", "internal email", "+39 1234567890", "washington st.", "new york", true, warehouseLocationRepo.getReferenceById(1)));
            userRepo.save(new User("shopass1", "shopass123", List.of(roleRepo.getReferenceById(2)), 1));

        }

        //customer
        //review
        //wishlist
        //notification
        //search history
        //cart
        //order
    }
}
