package ru.testfield.ansible.inventory.controller.api.dt;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.web.bind.annotation.*;
import ru.testfield.ansible.inventory.model.LoginUser;
import ru.testfield.ansible.inventory.model.LoginUserGroup;
import ru.testfield.ansible.inventory.repository.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/dataTables")
public class DataTablesApiController {

    private final LoginUserRepository loginUserRepository;
    private final LoginUserGroupRepository loginUserGroupRepository;


    public DataTablesApiController(LoginUserRepository loginUserRepository, LoginUserGroupRepository loginUserGroupRepository) {
        this.loginUserRepository = loginUserRepository;
        this.loginUserGroupRepository = loginUserGroupRepository;
    }

    @RequestMapping(value = "/loginUser", method = RequestMethod.POST)
    public DataTablesOutput<LoginUser> getLoginUsers(@Valid @RequestBody DataTablesInput input) {
        return loginUserRepository.findAll(input);
    }

    @RequestMapping(value = "/loginUserGroup", method = RequestMethod.POST)
    public DataTablesOutput<LoginUserGroup> getLoginUserGroups(@Valid @RequestBody DataTablesInput input) {
        return loginUserGroupRepository.findAll(input);
    }

}
