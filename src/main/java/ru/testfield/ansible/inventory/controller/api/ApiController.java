package ru.testfield.ansible.inventory.controller.api;

import org.springframework.web.bind.annotation.*;
import ru.testfield.ansible.inventory.repository.LoginUserGroupRepository;
import ru.testfield.ansible.inventory.repository.LoginUserRepository;

import java.util.UUID;

@RestController
@RequestMapping("/api/")
public class ApiController {

    private final LoginUserRepository loginUserRepository;
    private final LoginUserGroupRepository loginUserGroupRepository;

    public ApiController(LoginUserRepository loginUserRepository, LoginUserGroupRepository loginUserGroupRepository) {
        this.loginUserRepository = loginUserRepository;
        this.loginUserGroupRepository = loginUserGroupRepository;
    }

    @RequestMapping(value = "/loginUser/{id}", method = RequestMethod.DELETE)
    public void deleteLoginUser(@PathVariable UUID id) {
        loginUserRepository.deleteById(id);
    }

    @RequestMapping(value = "/loginUserGroup/{id}", method = RequestMethod.DELETE)
    public void deleteLoginUserGroup(@PathVariable UUID id) {
        loginUserGroupRepository.deleteById(id);
    }
}