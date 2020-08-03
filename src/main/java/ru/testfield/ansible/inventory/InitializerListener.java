package ru.testfield.ansible.inventory;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.testfield.ansible.inventory.model.LoginUser;
import ru.testfield.ansible.inventory.model.LoginUserGroup;
import ru.testfield.ansible.inventory.repository.LoginUserGroupRepository;
import ru.testfield.ansible.inventory.repository.LoginUserRepository;

import java.util.UUID;

@Component
public class InitializerListener {

    private final LoginUserRepository loginUserRepository;

    private final LoginUserGroupRepository loginUserGroupRepository;

    public InitializerListener(LoginUserRepository loginUserRepository, LoginUserGroupRepository loginUserGroupRepository) {
        this.loginUserRepository = loginUserRepository;
        this.loginUserGroupRepository = loginUserGroupRepository;
    }

    @EventListener({ContextRefreshedEvent.class})
    public void contextRefreshedEvent() {
        loginUserRepository.deleteAll();
        for(int i=0; i<100; i++) {
            LoginUser loginUser = new LoginUser(UUID.randomUUID(), "user"+i, "password", null);
            loginUserRepository.save(loginUser);
        }
        LoginUserGroup previous = null;
        loginUserGroupRepository.deleteAll();
        for(int i=0; i<20; i++) {
            LoginUserGroup loginUsergroup = new LoginUserGroup(UUID.randomUUID(), "group"+i, previous, null);
            loginUserGroupRepository.save(loginUsergroup);
            previous = loginUsergroup;
        }
    }

}
