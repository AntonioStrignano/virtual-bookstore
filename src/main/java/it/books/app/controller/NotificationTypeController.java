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

import it.books.app.model.NotificationType;
import it.books.app.repository.NotificationTypeRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/notification-types")
public class NotificationTypeController {

    @Autowired
    private NotificationTypeRepository notifTypeRepo;

    // ---- READ ----
    @GetMapping("")
    public String getAllNotificationTypes(Model model) {
        model.addAttribute("notificationTypes", notifTypeRepo.findAll());
        return "/notif-type/home";
    }

    // ---- CREATE ----
    @GetMapping("/create")
    public String newNotifType(Model model) {
        NotificationType notifType = new NotificationType();
        model.addAttribute("notificationType", notifType);
        return "/notif-type/edit";
    }

    @PostMapping("/create")
    public String createNotifType(@Valid @ModelAttribute("notificationType") NotificationType notifType, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/notif-type/edit";
        }

        notifTypeRepo.save(notifType);
        return "redirect:/notification-types";
    }

    // ---- UPDATE ----
    @GetMapping("/edit/{id}")
    public String editNotifType(@PathVariable("id") Integer id, Model model) {
        NotificationType notifType = notifTypeRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid notification type ID: " + id));
        model.addAttribute("notificationType", notifType);
        model.addAttribute("editMode", true);
        return "/notif-type/edit";
    }

    @PostMapping("/update")
    public String updateNotifType(@Valid @ModelAttribute("notificationType") NotificationType notifType, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/notif-type/edit";
        }

        notifTypeRepo.save(notifType);
        return "redirect:/notification-types";
    }

    // ---- DELETE ----
    @PostMapping("/delete/{id}")
    public String deleteNotifType(@PathVariable("id") Integer id) {
        NotificationType notifType = notifTypeRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid notification type ID: " + id));
        notifTypeRepo.delete(notifType);
        return "redirect:/notification-types";

    }
}
