package ru.testfield.ansible.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.testfield.ansible.model.AnsibleHostGroup;
import ru.testfield.ansible.repository.AnsibleHostGroupRepository;
import ru.testfield.web.controller.AbstractWebController;

import javax.validation.Valid;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("ansibleHostGroup")
public class AnsibleHostGroupController extends AbstractWebController {

    private final AnsibleHostGroupRepository ansibleHostGroupRepository;

    @Autowired
    public AnsibleHostGroupController(AnsibleHostGroupRepository ansibleHostGroupRepository) {
        this.ansibleHostGroupRepository = ansibleHostGroupRepository;
    }

    @RequestMapping(value = {"/", ""})
    public String index(Model model) {
        model.addAttribute("title", "AnsibleHostGroups list");
        return "ansible/pages/ansibleHostGroup/list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String ansibleHostAdd(Model model) {
        model.addAttribute("title","Add ansibleHostGroup");
        model.addAttribute("ansibleHostGroup", new AnsibleHostGroup());
        return "ansible/pages/ansibleHostGroup/edit";
    }

    @RequestMapping(value = "{id}/edit", method = RequestMethod.GET)
    public String ansibleHostEdit(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("title", "Edit ansible host group");
        Optional<AnsibleHostGroup> optionalAnsibleHostGroup = ansibleHostGroupRepository.findById(id);
        if (optionalAnsibleHostGroup.isEmpty()) {
            throw new NoSuchElementException("No such ansibleHostGroup: " + id);
        } else {
            model.addAttribute("ansibleHostGroup", optionalAnsibleHostGroup.get());
        }
        return "ansible/pages/ansibleHostGroup/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String ansibleHostGroupEdit() {
        return "redirect:";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String ansibleHostEditPost(@Valid AnsibleHostGroup ansibleHostGroup, BindingResult bindingResult, RedirectAttributes attr, Model model) {
        if(bindingResult.hasErrors()){
            processBindingResults(bindingResult, attr, model);
            return "ansible/pages/ansibleHostGroup/edit";
        } else {
            ansibleHostGroupRepository.save(ansibleHostGroup);
            flashSuccessNotification(attr);
            return "redirect:" + ansibleHostGroup.getId() + "/edit";
        }
    }
}