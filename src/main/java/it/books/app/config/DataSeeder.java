package it.books.app.config;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import it.books.app.model.AnalyticType;
import it.books.app.model.Analytic;
import it.books.app.model.Author;
import it.books.app.model.Award;
import it.books.app.model.Book;
import it.books.app.model.BookCollection;
import it.books.app.model.BookstoreRole;
import it.books.app.model.Cart;
import it.books.app.model.Customer;
import it.books.app.model.Discount;
import it.books.app.model.DiscountType;
import it.books.app.model.Edition;
import it.books.app.model.Format;
import it.books.app.model.Genre;
import it.books.app.model.Inventory;
import it.books.app.model.InventoryStatus;
import it.books.app.model.Notification;
import it.books.app.model.NotificationType;
import it.books.app.model.Order;
import it.books.app.model.Publisher;
import it.books.app.model.Review;
import it.books.app.model.Role;
import it.books.app.model.SearchHistory;
import it.books.app.model.ShopAssistant;
import it.books.app.model.Translator;
import it.books.app.model.User;
import it.books.app.model.WarehouseLocation;
import it.books.app.model.Wishlist;
import it.books.app.repository.AnalyticTypeRepository;
import it.books.app.repository.AuthorRepository;
import it.books.app.repository.AwardRepository;
import it.books.app.repository.BookCollectionRepository;
import it.books.app.repository.BookRepository;
import it.books.app.repository.BookstoreRoleRepository;
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
import it.books.app.repository.AnalyticRepository;

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
    private BookstoreRoleRepository bookRoleRepo;

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

    @Autowired
    private AnalyticRepository analytRepo;

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
            notificationTypeRepo.save(new NotificationType("Order Confirmation", "Your order has been confirmed!", "/order/{orderId}"));
            notificationTypeRepo.save(new NotificationType("Shipping Update", "Your order has been shipped!", "/order/{orderId}"));
            notificationTypeRepo.save(new NotificationType("Delivery Confirmation", "Your order has been delivered!", "/order/{orderId}"));
            notificationTypeRepo.save(new NotificationType("Promotional Offer", "Check out our latest offers!", "/book/{bookId}/detail"));
            notificationTypeRepo.save(new NotificationType("Feedback Request", "We value your feedback!", "/reviews/create/{bookId}"));
            notificationTypeRepo.save(new NotificationType("Order Cancellation", "Your order has been cancelled.", "/order/{orderId}"));
            notificationTypeRepo.save(new NotificationType("Wishlist Reminder", "Items in your wishlist are on sale!", "/book/{bookId}/detail"));
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
            analyticTypeRepo.save(new AnalyticType("Book Views", "searched for book"));
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

        //SECURITY ROLES
        if (roleRepo.count() == 0) {
            roleRepo.save(new Role("ADMIN"));
            roleRepo.save(new Role("SHOP_ASSISTANT"));
            roleRepo.save(new Role("CUSTOMER"));
        }

        //BOOKSTORE ROLES
        if (bookRoleRepo.count() == 0) {
            bookRoleRepo.save(new BookstoreRole("Shop Assistant"));
            bookRoleRepo.save(new BookstoreRole("Customer"));

        }

        // USERS
        {
            if (userRepo.count() == 0) {
                User admin = new User("admin", "admin123");
                List<Role> adminRoles = new ArrayList<>();
                adminRoles.add(roleRepo.getReferenceById(1));
                admin.setRoles(adminRoles);
                userRepo.save(admin);
            }
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

            authorRepo.save(new Author("Emily", "Wilson", LocalDate.of(1995, 8, 8), LocalDate.of(2021, 9, 9), "Mexican", "Author bio", "http://emilywilson.com", "http://emilywilson.com/profile.jpg", "http://emilywilson.com/social", true, genreRepo.getReferenceById(6)));
            authorRepo.save(new Author("David", "Garcia", LocalDate.of(1988, 10, 10), "Spanish", "Author bio", "http://davidgarcia.com", "http://davidgarcia.com/profile.jpg", "http://davidgarcia.com/social", false, genreRepo.getReferenceById(1)));
            authorRepo.save(new Author("Sophia", "Martinez", LocalDate.of(1992, 11, 11), LocalDate.of(2022, 12, 12), "Argentinian", "Author bio", "http://sophiamartinez.com", "http://sophiamartinez.com/profile.jpg", "http://sophiamartinez.com/social", true, genreRepo.getReferenceById(2)));
            authorRepo.save(new Author("Michael", "Lopez", LocalDate.of(1983, 1, 1), "Colombian", "Author bio", "http://michaellopez.com", "http://michaellopez.com/profile.jpg", "http://michaellopez.com/social", false, genreRepo.getReferenceById(3)));

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
            publisherRepo.save(new Publisher("Scholastic", 1920, "USA", "New York", "http://scholastic.com", "generic email", "303 Scholastic St, NY", "Publisher specializing in children's books"));
            publisherRepo.save(new Publisher("Oxford University Press", 1478, "UK", "Oxford", "http://oup.com", "generic email", "404 Oxford St, Oxford", "Publisher of academic and educational materials"));
            publisherRepo.save(new Publisher("Cambridge University Press", 1534, "UK", "Cambridge", "http://cambridge.org", "generic email", "505 Cambridge St, Cambridge", "Publisher of scholarly books and journals"));
            publisherRepo.save(new Publisher("Bloomsbury Publishing", 1986, "UK", "London", "http://bloomsbury.com", "generic email", "606 Bloomsbury St, London", "Publisher known for literary fiction and non-fiction"));
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

        //book collection 
        if (bookCollectionRepo.count() == 0) {
            bookCollectionRepo.save(new BookCollection("Classic Literature", "A collection of classic literary works", true, publisherRepo.getReferenceById(1)));
            bookCollectionRepo.save(new BookCollection("Fantasy Series", "A collection of popular fantasy series", true, publisherRepo.getReferenceById(2)));
            bookCollectionRepo.save(new BookCollection("Mystery Thrillers", "A collection of gripping mystery thrillers", false, publisherRepo.getReferenceById(3)));
        }

        //book
        if (bookRepo.count() == 0) {
            bookRepo.save(new Book("Unknown Book", authorRepo.getReferenceById(1), "0000000000000",
                    List.of(genreRepo.getReferenceById(1)), "Unknown", LocalDate.of(1900, 1, 1),
                    publisherRepo.getReferenceById(1), "No description available", "none",
                    formatRepo.getReferenceById(1), 100, "none", "All rights reserved", editionRepo.getReferenceById(1), null, null));
            bookRepo.save(new Book("The Great Gatsby", authorRepo.getReferenceById(2), "9780743273565",
                    List.of(genreRepo.getReferenceById(2)), "English", LocalDate.of(1925, 4, 10),
                    publisherRepo.getReferenceById(2), "A classic novel set in the Jazz Age",
                    "http://example.com/greatgatsby.jpg", formatRepo.getReferenceById(2), 180, "8.5 x 5.5 inches", "Copyright © 1925", editionRepo.getReferenceById(2), null, null));
            bookRepo.save(new Book("To Kill a Mockingbird", authorRepo.getReferenceById(3), "9780061120084",
                    List.of(genreRepo.getReferenceById(3)), "English", LocalDate.of(1960, 7, 11),
                    publisherRepo.getReferenceById(3), "A novel about racial injustice in the Deep South",
                    "http://example.com/tokillamockingbird.jpg", formatRepo.getReferenceById(3), 281, "8 x 5.25 inches", "Copyright © 1960",
                    editionRepo.getReferenceById(3), translatorRepo.getReferenceById(2), bookCollectionRepo.getReferenceById(1)));
            bookRepo.save(new Book("1984", authorRepo.getReferenceById(4), "9780451524935",
                    List.of(genreRepo.getReferenceById(4)), "English", LocalDate.of(1949, 6, 8),
                    publisherRepo.getReferenceById(4), "A dystopian novel about totalitarianism",
                    "http://example.com/1984.jpg", formatRepo.getReferenceById(4), 328, "7.5 x 4.2 inches", "Copyright © 1949", editionRepo.getReferenceById(4), translatorRepo.getReferenceById(1), null));
            bookRepo.save(new Book("Pride and Prejudice", authorRepo.getReferenceById(5), "9780141040349",
                    List.of(genreRepo.getReferenceById(1)), "English", LocalDate.of(1813, 1, 28), publisherRepo.getReferenceById(5),
                    "A romantic novel about manners and marriage", "http://example.com/prideandprejudice.jpg",
                    formatRepo.getReferenceById(5), 279, "7.8 x 5.1 inches", "Copyright © 1813", editionRepo.getReferenceById(2), translatorRepo.getReferenceById(3), null));

            bookRepo.save(new Book("The Catcher in the Rye", authorRepo.getReferenceById(6), "9780316769488",
                    List.of(genreRepo.getReferenceById(2)), "English", LocalDate.of(1951, 7, 16),
                    publisherRepo.getReferenceById(6), "A novel about teenage angst and alienation",
                    "http://example.com/catcherintherye.jpg", formatRepo.getReferenceById(6), 277, "8 x 5.31 inches", "Copyright © 1951", editionRepo.getReferenceById(3), null, null));
            bookRepo.save(new Book("The Hobbit", authorRepo.getReferenceById(7), "9780547928227",
                    List.of(genreRepo.getReferenceById(3)), "English", LocalDate.of(1937, 9, 21),
                    publisherRepo.getReferenceById(7), "A fantasy novel about a hobbit's adventure",
                    "http://example.com/thehobbit.jpg", formatRepo.getReferenceById(7), 310, "8.5 x 5.5 inches", "Copyright © 1937", editionRepo.getReferenceById(2), translatorRepo.getReferenceById(4), bookCollectionRepo.getReferenceById(2)));
            bookRepo.save(new Book("The Lord of the Rings", authorRepo.getReferenceById(8), "9780544003415",
                    List.of(genreRepo.getReferenceById(4)), "English", LocalDate.of(1954, 7, 29),
                    publisherRepo.getReferenceById(8), "An epic fantasy novel about the struggle to destroy a powerful ring",
                    "http://example.com/thelordoftherings.jpg", formatRepo.getReferenceById(8), 1216, "10.2 x 6.1 inches", "Copyright © 1954", editionRepo.getReferenceById(3), translatorRepo.getReferenceById(5), bookCollectionRepo.getReferenceById(2)));
            bookRepo.save(new Book("The Da Vinci Code", authorRepo.getReferenceById(9), "9780307474278",
                    List.of(genreRepo.getReferenceById(1)), "English", LocalDate.of(2003, 3, 18),
                    publisherRepo.getReferenceById(9), "A mystery thriller novel about a symbologist's quest to uncover a hidden truth",
                    "http://example.com/thedavincicode.jpg", formatRepo.getReferenceById(9), 489, "6.1 x 9.2 inches", "Copyright © 2003", editionRepo.getReferenceById(2), translatorRepo.getReferenceById(5), bookCollectionRepo.getReferenceById(3)));
            bookRepo.save(new Book("The Alchemist", authorRepo.getReferenceById(10), "9780062315007",
                    List.of(genreRepo.getReferenceById(2)), "English", LocalDate.of(1988, 4, 15),
                    publisherRepo.getReferenceById(10), "A philosophical novel about a shepherd's journey to find his personal legend",
                    "http://example.com/thealchemist.jpg", formatRepo.getReferenceById(10), 208, "5.5 x 8.2 inches", "Copyright © 1988", editionRepo.getReferenceById(5), translatorRepo.getReferenceById(6), bookCollectionRepo.getReferenceById(3)));
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
                userRepo.save(new User("shopass1", "shopass123", List.of(roleRepo.getReferenceById(2)), bookRoleRepo.getReferenceById(1), 1));

            }
            //wishlist
            if (wishRepo.count() == 0) {
                wishRepo.save(new Wishlist(List.of(bookRepo.getReferenceById(1), bookRepo.getReferenceById(2), bookRepo.getReferenceById(3))));
            }

            //cart
            if (cartRepo.count() == 0) {
                cartRepo.save(new Cart(List.of(inventoryRepo.getReferenceById(1), inventoryRepo.getReferenceById(2), inventoryRepo.getReferenceById(3))));
            }

            //customer
            if (custRepo.count() == 0) {

                custRepo.save(new Customer("Name", "Surname", " +123456789102", "address address", "{\"language\":\"en\",\"currency\":\"USD\",\"newsletterSubscribed\":true,\"preferredGenres\":[\"fiction\",\"science\",\"fantasy\"],\"darkMode\":false,\"notifications\":{\"email\":true,\"sms\":false,\"push\":true}}\r\n",
                        cartRepo.getReferenceById(1), wishRepo.getReferenceById(1))
                );
                userRepo.save(new User("cust1", "cust123", List.of(roleRepo.getReferenceById(3)), bookRoleRepo.getReferenceById(2), 1));
            }

            //review
            if (reviewRepo.count() == 0) {
                reviewRepo.save(new Review(5, "comment example", LocalDateTime.of(2025, 07, 21, 19, 20), bookRepo.getReferenceById(3), custRepo.getReferenceById(1)));
                reviewRepo.save(new Review(5, "comment example", LocalDateTime.of(2025, 06, 21, 07, 50), bookRepo.getReferenceById(4), custRepo.getReferenceById(1)));
            }

            //order
            if (orderRepo.count() == 0) {

                orderRepo.save(new Order(LocalDateTime.of(2025, 07, 07, 15, 20), LocalDateTime.of(2025, 07, 07, 16, 00), LocalDateTime.of(2025, 07, 10, 10, 00),
                        custRepo.getReferenceById(1), shopAssRepo.getReferenceById(1), new ArrayList<>(List.of(inventoryRepo.getReferenceById(1), inventoryRepo.getReferenceById(2)))));
                orderRepo.save(new Order(LocalDateTime.of(2025, 07, 8, 10, 00), null, null,
                        custRepo.getReferenceById(1), shopAssRepo.getReferenceById(1), new ArrayList<>(List.of(inventoryRepo.getReferenceById(3), inventoryRepo.getReferenceById(4)))));
                orderRepo.save(new Order(LocalDateTime.of(2025, 07, 9, 12, 30), LocalDateTime.of(2025, 07, 9, 13, 00), null,
                        custRepo.getReferenceById(1), shopAssRepo.getReferenceById(1), new ArrayList<>(List.of(inventoryRepo.getReferenceById(1), inventoryRepo.getReferenceById(3)))));
            }

            //notification
            if (notifiRepo.count() == 0) {
                notifiRepo.save(new Notification(LocalDateTime.of(2025, 07, 07, 20, 20), custRepo.getReferenceById(1), notificationTypeRepo.findById(1).get(), null, orderRepo.findById(1).get()));
                notifiRepo.save(new Notification(LocalDateTime.of(2025, 07, 07, 15, 10), custRepo.getReferenceById(1), notificationTypeRepo.findById(6).get(), null, null));
                notifiRepo.save(new Notification(LocalDateTime.of(2025, 07, 12, 10, 00), custRepo.getReferenceById(1), notificationTypeRepo.findById(7).get(), bookRepo.findById(3).get(), null));
            }

            //search history
            if (searchRepo.count() == 0) {
                searchRepo.save(new SearchHistory("gatsby", LocalDateTime.of(2025, 07, 07, 15, 20), custRepo.getReferenceById(1)));
                searchRepo.save(new SearchHistory("to kill", LocalDateTime.of(2025, 07, 07, 15, 20), custRepo.getReferenceById(1)));
                searchRepo.save(new SearchHistory("pride", LocalDateTime.of(2025, 07, 07, 15, 20), custRepo.getReferenceById(1)));
                searchRepo.save(new SearchHistory("1984", LocalDateTime.of(2025, 07, 07, 15, 20), custRepo.getReferenceById(1)));
            }

            // analytics
            if (analytRepo.count() == 0) {
                analytRepo.save(new Analytic(LocalDateTime.of(2025, 07, 07, 15, 20), bookRepo.findById(1).get(), custRepo.findById(1).get(), analyticTypeRepo.findById(1).get()));
                analytRepo.save(new Analytic(LocalDateTime.of(2025, 07, 07, 15, 20), bookRepo.findById(2).get(), custRepo.findById(1).get(), analyticTypeRepo.findById(2).get()));
                analytRepo.save(new Analytic(LocalDateTime.of(2025, 07, 07, 15, 20), bookRepo.findById(3).get(), custRepo.findById(1).get(), analyticTypeRepo.findById(3).get()));
                analytRepo.save(new Analytic(LocalDateTime.of(2025, 07, 07, 15, 20), bookRepo.findById(4).get(), custRepo.findById(1).get(), analyticTypeRepo.findById(4).get()));
                analytRepo.save(new Analytic(LocalDateTime.of(2025, 07, 07, 15, 20), bookRepo.findById(5).get(), custRepo.findById(1).get(), analyticTypeRepo.findById(5).get()));
            }

        }

    }
}
