package ru.testfield.ansible.inventory.repository;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import ru.testfield.ansible.inventory.model.LoginUserRole;

import java.util.UUID;

public interface LoginUserRoleRepository extends DataTablesRepository<LoginUserRole, UUID> {

}