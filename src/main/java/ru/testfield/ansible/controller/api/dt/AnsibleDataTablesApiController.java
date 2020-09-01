package ru.testfield.ansible.controller.api.dt;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.testfield.ansible.model.AnsibleHost;
import ru.testfield.ansible.repository.AnsibleHostRepository;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/dataTables")
public class AnsibleDataTablesApiController {

    private final AnsibleHostRepository ansibleHostRepository;


    public AnsibleDataTablesApiController(AnsibleHostRepository ansibleHostRepository) {
        this.ansibleHostRepository = ansibleHostRepository;
    }

    @RequestMapping(value = "/ansibleHost", method = RequestMethod.POST)
    public DataTablesOutput<AnsibleHost> getAnsibleHosts(@Valid @RequestBody DataTablesInput input) {
        return ansibleHostRepository.findAll(input);
    }

}