package ru.testfield.ansible.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.testfield.ansible.inventory.model.AnsibleUser;

import java.util.UUID;


public interface UserRepository extends JpaRepository<AnsibleUser, UUID> {
}
