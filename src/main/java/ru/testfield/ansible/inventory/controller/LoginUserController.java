package ru.testfield.ansible.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.testfield.ansible.inventory.model.LoginUser;
import ru.testfield.ansible.inventory.repository.LoginUserGroupRepository;
import ru.testfield.ansible.inventory.repository.LoginUserRepository;
import ru.testfield.ansible.inventory.model.Notification;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("loginUser")
public class LoginUserController {

    private final LoginUserRepository loginUserRepository;

    private final LoginUserGroupRepository loginUserGroupRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public LoginUserController(LoginUserRepository loginUserRepository, LoginUserGroupRepository loginUserGroupRepository, PasswordEncoder passwordEncoder) {
        this.loginUserRepository = loginUserRepository;
        this.loginUserGroupRepository = loginUserGroupRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @ModelAttribute
    private void processFlashAttributes(RedirectAttributes attr, Model model) {
        if (attr.getFlashAttributes().get("notifications") != null) {
            model.addAttribute("notifications", attr.getFlashAttributes().get("notifications"));
        }
    }

    @RequestMapping(value = {"/", ""})
    public String index(Model model) {
        model.addAttribute("title", "LoginUsers list");
        return "pages/loginUser/list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String userAdd(Model model) {
        model.addAttribute("title","Add loginUser");
        model.addAttribute("loginUser", new LoginUser());
        model.addAttribute("loginUserGroups", loginUserGroupRepository.findAll());
        return "pages/loginUser/edit";
    }

    @RequestMapping(value = "{id}/edit", method = RequestMethod.GET)
    public String userEdit(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("title", "Edit loginUser");
        Optional<LoginUser> optionalLoginUser = loginUserRepository.findById(id);
        if (optionalLoginUser.isEmpty()) {
            throw new NoSuchElementException("No such loginUser: " + id);
        } else {
            model.addAttribute("loginUser", optionalLoginUser.get());
            model.addAttribute("loginUserGroups", loginUserGroupRepository.findAll());
        }
        return "pages/loginUser/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String userEdit() {
        return "redirect:";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String userEditPost(@Valid LoginUser loginUser, BindingResult bindingResult, RedirectAttributes attr, Model model) {
        if(bindingResult.hasErrors()){
            List<Notification> notifications = bindingResult.getFieldErrors()
                    .stream()
                    .map(fieldError -> "'"+fieldError.getField()+": "+fieldError.getDefaultMessage())
                    .map(message -> new Notification(Notification.NotificationType.WARNING, message))
                    .collect(Collectors.toList());
            attr.addFlashAttribute("notifications", notifications);
            model.addAttribute("loginUserGroups", loginUserGroupRepository.findAll());
            return "pages/loginUser/edit";
        }
        if(loginUser.getPasswordBcryptHash()!=null && !loginUser.getPasswordBcryptHash().isEmpty()) {
            loginUser.setPasswordBcryptHash(passwordEncoder.encode(loginUser.getPasswordBcryptHash()));
        } else {
            if(loginUser.getId()!=null) {
                Optional<LoginUser> optionalStoredLoginUser = loginUserRepository.findById(loginUser.getId());
                optionalStoredLoginUser.ifPresent(user -> loginUser.setPasswordBcryptHash(user.getPasswordBcryptHash()));
            }
        }

        loginUserRepository.save(loginUser);
        attr.addFlashAttribute("notifications",
                Collections.singletonList(new Notification(Notification.NotificationType.SUCCESS, "Changes applied"))
        );
        return "redirect:" + loginUser.getId() + "/edit";
    }

}