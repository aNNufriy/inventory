package ru.testfield.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.testfield.web.model.LoginUserRole;
import ru.testfield.web.repository.LoginUserRoleRepository;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("loginUserRole")
public class LoginUserRoleController extends AbstractWebController {

    private final LoginUserRoleRepository loginUserRoleRepository;

    @Autowired
    public LoginUserRoleController(LoginUserRoleRepository loginUserRoleRepository) {
        this.loginUserRoleRepository = loginUserRoleRepository;
    }

    @RequestMapping(value = {"/",""} )
    public String index(Model model){
        model.addAttribute("title","LoginUserRoles list");
        return "web/pages/loginUserRole/list";
    }

    @RequestMapping(value = "/add", method= RequestMethod.GET)
    public String loginUserRoleAdd(Model model){
        model.addAttribute("title","Add loginUserRole");
        model.addAttribute("loginUserRole", new LoginUserRole());
        model.addAttribute("loginUserRoles", loginUserRoleRepository.findAll());
        return "web/pages/loginUserRole/edit";
    }

    @RequestMapping(value = "{id}/edit", method= RequestMethod.GET)
    public String loginUserRoleEdit(@PathVariable("id") UUID id, Model model){
        model.addAttribute("title","Edit loginUserRole");

        Optional<LoginUserRole> optionalLoginUser = loginUserRoleRepository.findById(id);
        if(optionalLoginUser.isEmpty()){
            throw new NoSuchElementException("No such loginUserRole: " + id);
        } else {
            model.addAttribute("loginUserRole", optionalLoginUser.get());
            model.addAttribute("loginUserRoles", loginUserRoleRepository.findAll());
        }
        return "web/pages/loginUserRole/edit";
    }

    @RequestMapping(value = "/edit", method= RequestMethod.GET)
    public String userEdit(){
        return "redirect:";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String loginUserRoleEditPost(@Valid LoginUserRole loginUserRole, BindingResult bindingResult, RedirectAttributes attr, Model model) {
        if(bindingResult.hasErrors()){
            processBindingResults(bindingResult, attr, model);
            model.addAttribute("loginUserRoles", loginUserRoleRepository.findAll());
            return "web/pages/loginUserRole/edit";
        } else {
            loginUserRoleRepository.save(loginUserRole);
            flashSuccessNotification(attr);
            return "redirect:" + loginUserRole.getId() + "/edit";
        }
    }

}