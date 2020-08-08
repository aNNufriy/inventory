package ru.testfield.ansible.inventory.controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.testfield.ansible.inventory.model.Notification;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AbstractWebController {
    @ModelAttribute
    private void processFlashAttributes(RedirectAttributes attr, Model model){
        if(attr.getFlashAttributes().get("notifications")!=null){
            model.addAttribute("notifications",attr.getFlashAttributes().get("notifications"));
        }
    }

    protected void flashSuccessNotification(RedirectAttributes attr) {
        attr.addFlashAttribute("notifications", Collections.singletonList(
                new Notification(Notification.NotificationType.SUCCESS, "Changes applied")
        ));
    }

    protected void processBindingResults(BindingResult bindingResult, RedirectAttributes attr, Model model) {
        model.addAttribute("title", "ERROR!");
        List<Notification> notifications = bindingResult.getFieldErrors()
                .stream()
                .map(fieldError -> "'"+fieldError.getField()+": "+fieldError.getDefaultMessage())
                .map(message -> new Notification(Notification.NotificationType.WARNING, message))
                .collect(Collectors.toList());
        attr.addFlashAttribute("notifications", notifications);
    }
}
