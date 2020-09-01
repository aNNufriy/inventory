package ru.testfield.ansible.repository;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import ru.testfield.ansible.model.AnsibleHost;

import java.util.UUID;

public interface AnsibleHostRepository extends DataTablesRepository<AnsibleHost, UUID> {
    AnsibleHost findByHostName(String hostName);
}