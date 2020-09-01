package ru.testfield.web.controller.api;

import org.springframework.web.bind.annotation.*;
import ru.testfield.web.repository.LoginUserRoleRepository;
import ru.testfield.web.repository.LoginUserRepository;

import java.util.UUID;

@RestController
@RequestMapping("/api/")
public class WebApiController {

    private final LoginUserRepository loginUserRepository;
    private final LoginUserRoleRepository loginUserRoleRepository;

    public WebApiController(LoginUserRepository loginUserRepository, LoginUserRoleRepository loginUserRoleRepository) {
        this.loginUserRepository = loginUserRepository;
        this.loginUserRoleRepository = loginUserRoleRepository;
    }

    @RequestMapping(value = "/loginUser/{id}", method = RequestMethod.DELETE)
    public void deleteLoginUser(@PathVariable UUID id) {
        loginUserRepository.deleteById(id);
    }

    @RequestMapping(value = "/loginUserRole/{id}", method = RequestMethod.DELETE)
    public void deleteLoginUserRole(@PathVariable UUID id) {
        loginUserRoleRepository.deleteById(id);
    }

}