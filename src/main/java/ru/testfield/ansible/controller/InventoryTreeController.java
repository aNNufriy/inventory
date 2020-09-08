package ru.testfield.ansible.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InventoryTreeController {

    @RequestMapping("/tree")
    public String index(){
        return "ansible/pages/hostTree";
    }
}
