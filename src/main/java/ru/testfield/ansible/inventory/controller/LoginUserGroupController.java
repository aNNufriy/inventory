package ru.testfield.ansible.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.testfield.ansible.inventory.model.LoginUserGroup;
import ru.testfield.ansible.inventory.model.Notification;
import ru.testfield.ansible.inventory.repository.LoginUserGroupRepository;

import java.util.*;

@Controller
@RequestMapping("loginUserGroup")
public class LoginUserGroupController {

    private final LoginUserGroupRepository loginUserGroupRepository;

    @Autowired
    public LoginUserGroupController(LoginUserGroupRepository loginUserGroupRepository) {
        this.loginUserGroupRepository = loginUserGroupRepository;
    }

    @ModelAttribute
    private void processFlashAttributes(RedirectAttributes attr, Model model){
        if(attr.getFlashAttributes().get("notifications")!=null){
            model.addAttribute("notifications",attr.getFlashAttributes().get("notifications"));
        }
    }

    @RequestMapping(value = {"/",""} )
    public String index(Model model){
        model.addAttribute("title","LoginUserGroups list");
        return "pages/loginUserGroup/list";
    }

    @RequestMapping(value = "/add", method= RequestMethod.GET)
    public String loginUserAdd(Model model){
        model.addAttribute("title","Add loginUserGroup");
        model.addAttribute("loginUserGroup", new LoginUserGroup());
        model.addAttribute("loginUserGroups", loginUserGroupRepository.findAll());
        return "pages/loginUserGroup/edit";
    }

    @RequestMapping(value = "{id}/edit", method= RequestMethod.GET)
    public String loginUserEdit(@PathVariable("id") UUID id, Model model){
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


    @RequestMapping(value = "/edit", method= RequestMethod.POST)
    public String userEditPost(@ModelAttribute LoginUserGroup loginUser, RedirectAttributes attr){
        if(loginUser.getParent()!=null && loginUser.getParent().getId()==null){
            loginUser.setParent(null);
        }
        loginUserGroupRepository.save(loginUser);
        attr.addFlashAttribute("notifications",
                Collections.singletonList(new Notification(Notification.NotificationType.SUCCESS, "Changes applied"))
        );
        return "redirect:"+loginUser.getId()+"/edit";
    }

}
