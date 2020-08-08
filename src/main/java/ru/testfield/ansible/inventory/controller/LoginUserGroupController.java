package ru.testfield.ansible.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.testfield.ansible.inventory.model.LoginUser;
import ru.testfield.ansible.inventory.model.LoginUserGroup;
import ru.testfield.ansible.inventory.model.Notification;
import ru.testfield.ansible.inventory.repository.LoginUserGroupRepository;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("loginUserGroup")
public class LoginUserGroupController extends AbstractWebController {

    private final LoginUserGroupRepository loginUserGroupRepository;

    @Autowired
    public LoginUserGroupController(LoginUserGroupRepository loginUserGroupRepository) {
        this.loginUserGroupRepository = loginUserGroupRepository;
    }

    @RequestMapping(value = {"/",""} )
    public String index(Model model){
        model.addAttribute("title","LoginUserGroups list");
        return "pages/loginUserGroup/list";
    }

    @RequestMapping(value = "/add", method= RequestMethod.GET)
    public String loginUserGroupAdd(Model model){
        model.addAttribute("title","Add loginUserGroup");
        model.addAttribute("loginUserGroup", new LoginUserGroup());
        model.addAttribute("loginUserGroups", loginUserGroupRepository.findAll());
        return "pages/loginUserGroup/edit";
    }

    @RequestMapping(value = "{id}/edit", method= RequestMethod.GET)
    public String loginUserGroupEdit(@PathVariable("id") UUID id, Model model){
        model.addAttribute("title","Edit loginUserGroup");

        Optional<LoginUserGroup> optionalLoginUser = loginUserGroupRepository.findById(id);
        if(optionalLoginUser.isEmpty()){
            throw new NoSuchElementException("No such loginUserGroup: " + id);
        } else {
            model.addAttribute("loginUserGroup", optionalLoginUser.get());
            model.addAttribute("loginUserGroups", loginUserGroupRepository.findAll());
        }
        return "pages/loginUserGroup/edit";
    }

    @RequestMapping(value = "/edit", method= RequestMethod.GET)
    public String userEdit(){
        return "redirect:";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String loginUserGroupEditPost(@Valid LoginUserGroup loginUserGroup, BindingResult bindingResult, RedirectAttributes attr, Model model) {
        if(bindingResult.hasErrors()){
            processBindingResults(bindingResult, attr, model);
            model.addAttribute("loginUserGroups", loginUserGroupRepository.findAll());
            return "pages/loginUserGroup/edit";
        } else {
            loginUserGroupRepository.save(loginUserGroup);
            flashSuccessNotification(attr);
            return "redirect:" + loginUserGroup.getId() + "/edit";
        }
    }

}
