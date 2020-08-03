package ru.testfield.ansible.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.testfield.ansible.inventory.model.LoginUserGroup;
import ru.testfield.ansible.inventory.model.Notification;
import ru.testfield.ansible.inventory.repository.LoginUserGroupRepository;

import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

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
    public String itemAdd(Model model){
        model.addAttribute("title","Add loginUserGroup");
        return "pages/loginUserGroup/edit";
    }

    @RequestMapping(value = "{id}/edit", method= RequestMethod.GET)
    public String itemEdit(@PathVariable("id") UUID id, Model model, RedirectAttributes attr){
        model.addAttribute("title","Edit loginUserGroup");
        Optional<LoginUserGroup> optionalItem = loginUserGroupRepository.findById(id);
        if(optionalItem.isEmpty()){
            throw new NoSuchElementException("No such loginUserGroup: "+id);
        }else {
            model.addAttribute("loginUserGroup", optionalItem.get());
        }
        return "pages/loginUserGroup/edit";
    }

    @RequestMapping(value = "/edit", method= RequestMethod.GET)
    public String userEdit(){
        return "redirect:";
    }

    @RequestMapping(value = "/edit", method= RequestMethod.POST)
    public String userEditPost(@ModelAttribute LoginUserGroup item, RedirectAttributes attr){
        loginUserGroupRepository.save(item);
        attr.addFlashAttribute("notifications",
                Collections.singletonList(new Notification(Notification.NotificationType.SUCCESS, "Changes applied"))
        );
        return "redirect:"+item.getId()+"/edit";
    }

}
