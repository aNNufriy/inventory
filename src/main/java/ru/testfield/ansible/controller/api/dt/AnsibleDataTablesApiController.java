package ru.testfield.ansible.controller.api.dt;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.testfield.ansible.model.AnsibleHost;
import ru.testfield.ansible.model.AnsibleHostGroup;
import ru.testfield.ansible.repository.AnsibleHostGroupRepository;
import ru.testfield.ansible.repository.AnsibleHostRepository;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/dataTables")
public class AnsibleDataTablesApiController {

    private final AnsibleHostRepository ansibleHostRepository;

    private final AnsibleHostGroupRepository ansibleHostGroupRepository;


    public AnsibleDataTablesApiController(AnsibleHostRepository ansibleHostRepository, AnsibleHostGroupRepository ansibleHostGroupRepository) {
        this.ansibleHostRepository = ansibleHostRepository;
        this.ansibleHostGroupRepository = ansibleHostGroupRepository;
    }

    @RequestMapping(value = "/ansibleHost", method = RequestMethod.POST)
    public DataTablesOutput<AnsibleHost> getAnsibleHosts(@Valid @RequestBody DataTablesInput input) {
        return ansibleHostRepository.findAll(input);
    }

    @RequestMapping(value = "/ansibleHostGroup", method = RequestMethod.POST)
    public DataTablesOutput<AnsibleHostGroup> getAnsibleHostGroups(@Valid @RequestBody DataTablesInput input) {
        return ansibleHostGroupRepository.findAll(input);
    }

}