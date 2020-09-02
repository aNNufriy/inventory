package ru.testfield.ansible.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.testfield.ansible.model.AnsibleHost;
import ru.testfield.ansible.repository.AnsibleHostRepository;
import ru.testfield.web.controller.AbstractWebController;

import javax.validation.Valid;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("ansibleHost")
public class AnsibleHostController extends AbstractWebController {

    private final AnsibleHostRepository ansibleHostRepository;

    @Autowired
    public AnsibleHostController(AnsibleHostRepository ansibleHostRepository) {
        this.ansibleHostRepository = ansibleHostRepository;
    }

    @RequestMapping(value = {"/", ""})
    public String index(Model model) {
        model.addAttribute("title", "AnsibleHosts list");
        return "ansible/pages/ansibleHost/list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String ansibleHostAdd(Model model) {
        model.addAttribute("title","Add ansibleHost");
        model.addAttribute("ansibleHost", new AnsibleHost());
        return "ansible/pages/ansibleHost/edit";
    }

    @RequestMapping(value = "{id}/edit", method = RequestMethod.GET)
    public String ansibleHostEdit(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("title", "Edit ansible host");
        Optional<AnsibleHost> optionalAnsibleHost = ansibleHostRepository.findById(id);
        if (optionalAnsibleHost.isEmpty()) {
            throw new NoSuchElementException("No such ansibleHost: " + id);
        } else {
            model.addAttribute("ansibleHost", optionalAnsibleHost.get());
        }
        return "ansible/pages/ansibleHost/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String ansibleHostEdit() {
        return "redirect:";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String ansibleHostEditPost(@Valid AnsibleHost ansibleHost, BindingResult bindingResult, RedirectAttributes attr, Model model) {
        if(bindingResult.hasErrors()){
            processBindingResults(bindingResult, attr, model);
            return "ansible/pages/ansibleHost/edit";
        } else {
            ansibleHostRepository.save(ansibleHost);
            flashSuccessNotification(attr);
            return "redirect:" + ansibleHost.getId() + "/edit";
        }
    }
}