package ru.testfield.web.controller.api.dt;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.web.bind.annotation.*;
import ru.testfield.web.model.LoginUser;
import ru.testfield.web.model.LoginUserRole;
import ru.testfield.web.repository.LoginUserRepository;
import ru.testfield.web.repository.LoginUserRoleRepository;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/dataTables")
public class WebDataTablesApiController {

    private final LoginUserRepository loginUserRepository;
    private final LoginUserRoleRepository loginUserRoleRepository;


    public WebDataTablesApiController(LoginUserRepository loginUserRepository, LoginUserRoleRepository loginUserRoleRepository) {
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