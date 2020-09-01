package ru.testfield.web.repository;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import ru.testfield.web.model.LoginUserRole;

import java.util.UUID;

public interface LoginUserRoleRepository extends DataTablesRepository<LoginUserRole, UUID> {

}