package ru.testfield.ansible.inventory.controller.api.dt;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.web.bind.annotation.*;
import ru.testfield.ansible.inventory.model.LoginUser;
import ru.testfield.ansible.inventory.repository.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/dataTables")
public class DataTablesApiController {

    private final LoginUserRepository loginUserRepository;

    public DataTablesApiController(LoginUserRepository loginUserRepository) {
        this.loginUserRepository = loginUserRepository;
    }

    @RequestMapping(value = "/loginUsers", method = RequestMethod.POST)
    public DataTablesOutput<LoginUser> getUsers(@Valid @RequestBody DataTablesInput input) {
        return loginUserRepository.findAll(input);
    }
    
    @RequestMapping(value = "/loginUsers/{userId}", method = RequestMethod.DELETE)
    public void deleteItem(@PathVariable UUID userId) {
        loginUserRepository.deleteById(userId);
    }

}
