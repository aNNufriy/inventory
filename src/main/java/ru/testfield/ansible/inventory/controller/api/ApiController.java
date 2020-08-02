package ru.testfield.ansible.inventory.controller.api;

import org.springframework.web.bind.annotation.*;
import ru.testfield.ansible.inventory.repository.LoginUserRepository;

import java.util.UUID;

@RestController
@RequestMapping("/api/")
public class ApiController {

    private final LoginUserRepository loginUserRepository;

    public ApiController(LoginUserRepository loginUserRepository) {
        this.loginUserRepository = loginUserRepository;
    }

    @RequestMapping(value = "/loginUser/{userId}", method = RequestMethod.DELETE)
    public void deleteItem(@PathVariable UUID userId) {
        loginUserRepository.deleteById(userId);
    }

}