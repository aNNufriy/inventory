package ru.testfield.ansible.inventory.controller;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.web.bind.annotation.*;
import ru.testfield.ansible.inventory.model.LoginUser;
import ru.testfield.ansible.inventory.model.AnsibleUser;
import ru.testfield.ansible.inventory.repository.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final LoginGroupRepository loginGroupRepository;
    private final LoginUserRepository loginUserRepository;

    public ApiController(LoginUserRepository loginUserRepository, LoginGroupRepository loginGroupRepository) {
        this.loginUserRepository = loginUserRepository;
        this.loginGroupRepository = loginGroupRepository;
    }

    @RequestMapping(value = "/loginUsers", method = RequestMethod.POST)
    public DataTablesOutput<LoginUser> getUsers(@Valid @RequestBody DataTablesInput input) {
        return loginUserRepository.findAll(input);
    }

}
