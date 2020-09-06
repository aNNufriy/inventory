package ru.testfield.ansible.repository;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import ru.testfield.ansible.model.AnsibleHostGroup;

import java.util.Optional;
import java.util.UUID;

public interface AnsibleHostGroupRepository extends DataTablesRepository<AnsibleHostGroup, UUID> {
    Optional<AnsibleHostGroup> findByName(String name);
}