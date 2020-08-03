package ru.testfield.ansible.inventory.repository;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import ru.testfield.ansible.inventory.model.LoginUserGroup;

import java.util.UUID;

public interface LoginUserGroupRepository extends DataTablesRepository<LoginUserGroup, UUID> {
}