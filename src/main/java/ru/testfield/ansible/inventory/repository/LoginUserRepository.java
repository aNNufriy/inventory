package ru.testfield.ansible.inventory.repository;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import ru.testfield.ansible.inventory.model.LoginUser;

import java.util.UUID;

public interface LoginUserRepository extends DataTablesRepository<LoginUser, UUID> {
}