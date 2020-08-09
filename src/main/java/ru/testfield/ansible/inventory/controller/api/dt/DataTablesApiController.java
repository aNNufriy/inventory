package ru.testfield.ansible.inventory.controller.api.dt;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.web.bind.annotation.*;
import ru.testfield.ansible.inventory.model.LoginUser;
import ru.testfield.ansible.inventory.model.LoginUserRole;
import ru.testfield.ansible.inventory.repository.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/dataTables")
public class DataTablesApiController {

    private final LoginUserRepository loginUserRepository;
    private final LoginUserRoleRepository loginUserRoleRepository;


    public DataTablesApiController(LoginUserRepository loginUserRepository, LoginUserRoleRepository loginUserRoleRepository) {
        this.loginUserRepository = loginUserRepository;
        this.loginUserRoleRepository = loginUserRoleRepository;
    }

    @RequestMapping(value = "/loginUser", method = RequestMethod.POST)
    public DataTablesOutput<LoginUser> getLoginUsers(@Valid @RequestBody DataTablesInput input) {
        return loginUserRepository.findAll(input);
    }

    @RequestMapping(value = "/loginUserRole", method = RequestMethod.POST)
    public DataTablesOutput<LoginUserRole> getLoginUserRoles(@Valid @RequestBody DataTablesInput input) {
        return loginUserRoleRepository.findAll(input);
    }

}
