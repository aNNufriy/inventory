package ru.testfield.ansible.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.testfield.ansible.inventory.model.LoginUser;
import ru.testfield.ansible.inventory.repository.LoginUserRoleRepository;
import ru.testfield.ansible.inventory.repository.LoginUserRepository;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("loginUser")
public class LoginUserController extends AbstractWebController {

    private final LoginUserRepository loginUserRepository;

    private final LoginUserRoleRepository loginUserRoleRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public LoginUserController(LoginUserRepository loginUserRepository, LoginUserRoleRepository loginUserRoleRepository, PasswordEncoder passwordEncoder) {
        this.loginUserRepository = loginUserRepository;
        this.loginUserRoleRepository = loginUserRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping(value = {"/", ""})
    public String index(Model model) {
        model.addAttribute("title", "LoginUsers list");
        return "pages/loginUser/list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String loginUserAdd(Model model) {
        model.addAttribute("title","Add loginUser");
        model.addAttribute("loginUser", new LoginUser());
        model.addAttribute("loginUserRoles", loginUserRoleRepository.findAll());
        return "pages/loginUser/edit";
    }

    @RequestMapping(value = "{id}/edit", method = RequestMethod.GET)
    public String loginUserEdit(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("title", "Edit loginUser");
        Optional<LoginUser> optionalLoginUser = loginUserRepository.findById(id);
        if (optionalLoginUser.isEmpty()) {
            throw new NoSuchElementException("No such loginUser: " + id);
        } else {
            model.addAttribute("loginUser", optionalLoginUser.get());
            model.addAttribute("loginUserRoles", loginUserRoleRepository.findAll());
        }
        return "pages/loginUser/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String loginUserEdit() {
        return "redirect:";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String loginUserEditPost(@Valid LoginUser loginUser, BindingResult bindingResult, RedirectAttributes attr, Model model) {
        if(bindingResult.hasErrors()){
            processBindingResults(bindingResult, attr, model);
            model.addAttribute("loginUserRoles", loginUserRoleRepository.findAll());
            return "pages/loginUser/edit";
        } else {
            processLoginUserPassword(loginUser);
            loginUserRepository.save(loginUser);
            flashSuccessNotification(attr);
            return "redirect:" + loginUser.getId() + "/edit";
        }
    }

    private void processLoginUserPassword(@Valid LoginUser loginUser) {
        if(loginUser.getPasswordBcryptHash()!=null && !loginUser.getPasswordBcryptHash().isEmpty()) {
            loginUser.setPasswordBcryptHash(passwordEncoder.encode(loginUser.getPasswordBcryptHash()));
        } else {
            if(loginUser.getId()!=null) {
                Optional<LoginUser> optionalStoredLoginUser = loginUserRepository.findById(loginUser.getId());
                optionalStoredLoginUser.ifPresent(user -> loginUser.setPasswordBcryptHash(user.getPasswordBcryptHash()));
            }
        }
    }
}