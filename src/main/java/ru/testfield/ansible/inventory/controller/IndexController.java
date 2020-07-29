package ru.testfield.ansible.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.testfield.ansible.inventory.model.AnsibleUser;
import ru.testfield.ansible.inventory.repository.UserRepository;

import java.util.List;

@Controller
public class IndexController {

    private final UserRepository userRepository;

    @Autowired
    public IndexController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping("/")
    public String index(){
        return "index";
    }
}
