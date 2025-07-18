package it.books.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import it.books.app.model.AnalyticType;
import it.books.app.model.Format;
import it.books.app.model.Genre;
import it.books.app.model.InventoryStatus;
import it.books.app.model.NotificationType;
import it.books.app.model.WarehouseLocation;
import it.books.app.repository.AnalyticTypeRepository;
import it.books.app.repository.FormatRepository;
import it.books.app.repository.GenreRepository;
import it.books.app.repository.InventoryStatusRepository;
import it.books.app.repository.NotificationTypeRepository;
import it.books.app.repository.WarehouseLocationRepository;

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

    @Override
    public void run(String... args) throws Exception {
        if (warehouseLocationRepo.count() == 0) {
            warehouseLocationRepo.save(new WarehouseLocation("Main Warehouse"));
            warehouseLocationRepo.save(new WarehouseLocation("Secondary Warehouse"));
        }

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
        if (inventoryStatusRepo.count() == 0) {
            inventoryStatusRepo.save(new InventoryStatus("Available", "Item is available for sale"));
            inventoryStatusRepo.save(new InventoryStatus("Out of Stock", "Item is currently out of stock"));
            inventoryStatusRepo.save(new InventoryStatus("Discontinued", "Item is no longer available"));
        }

        if (genreRepo.count() == 0) {
            genreRepo.save(new Genre("Fiction"));
            genreRepo.save(new Genre("Non-Fiction"));
            genreRepo.save(new Genre("Science Fiction"));
            genreRepo.save(new Genre("Fantasy"));
            genreRepo.save(new Genre("Mystery"));
            genreRepo.save(new Genre("Biography"));
        }

        if (formatRepo.count() == 0) {
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
    }

}
