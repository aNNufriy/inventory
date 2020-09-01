package ru.testfield.web.repository;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import ru.testfield.web.model.LoginUser;

import java.util.UUID;

public interface LoginUserRepository extends DataTablesRepository<LoginUser, UUID> {
    LoginUser findByLogin(String username);
}