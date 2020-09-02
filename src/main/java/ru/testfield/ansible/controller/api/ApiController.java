package ru.testfield.ansible.controller.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.testfield.ansible.repository.AnsibleHostGroupRepository;
import ru.testfield.ansible.repository.AnsibleHostRepository;

import java.util.UUID;

@RestController
@RequestMapping("/api/")
public class ApiController {

    private final AnsibleHostRepository ansibleHostRepository;

    private final AnsibleHostGroupRepository ansibleHostGroupRepository;

    public ApiController(AnsibleHostRepository ansibleHostRepository, AnsibleHostGroupRepository ansibleHostGroupRepository) {
        this.ansibleHostRepository = ansibleHostRepository;
        this.ansibleHostGroupRepository = ansibleHostGroupRepository;
    }

    @RequestMapping(value = "/ansibleHost/{id}", method = RequestMethod.DELETE)
    public void deleteAnsibleHost(@PathVariable UUID id) {
        ansibleHostRepository.deleteById(id);
    }

    @RequestMapping(value = "/ansibleHostGroup/{id}", method = RequestMethod.DELETE)
    public void deleteAnsibleHostGroup(@PathVariable UUID id) {
        ansibleHostGroupRepository.deleteById(id);
    }

}