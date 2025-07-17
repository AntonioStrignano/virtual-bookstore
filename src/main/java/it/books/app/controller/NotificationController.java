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
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationTypeRepository notifTypeRepo;

    // ---- READ ----
    @GetMapping("")
    public String getAllNotifications(Model model) {
        model.addAttribute("notifications", notifTypeRepo.findAll());
        return "/notification/home";
    }

    // ---- CREATE ----
    @GetMapping("/create")
    public String newNotification(Model model) {
        NotificationType notifType = new NotificationType();
        model.addAttribute("notification", notifType);
        return "/notification/edit";
    }

    @PostMapping("/create")
    public String createNotification(@Valid @ModelAttribute("notification") NotificationType notifType, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/notification/edit";
        }

        notifTypeRepo.save(notifType);
        return "redirect:/notifications";
    }

    // ---- UPDATE ----
    @GetMapping("/edit/{id}")
    public String editNotification(@PathVariable("id") Integer id, Model model) {
        NotificationType notifType = notifTypeRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid notification ID: " + id));
        model.addAttribute("notification", notifType);
        model.addAttribute("editMode", true);
        return "/notification/edit";
    }

    @PostMapping("/update")
    public String updateNotification(@Valid @ModelAttribute("notification") NotificationType notifType, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/notification/edit";
        }

        notifTypeRepo.save(notifType);
        return "redirect:/notifications";
    }

    // ---- DELETE ----
    @GetMapping("/delete/{id}")
    public String deleteNotification(@PathVariable("id") Integer id) {
        NotificationType notifType = notifTypeRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid notification ID: " + id));
        notifTypeRepo.delete(notifType);
        return "redirect:/notifications";
    }
