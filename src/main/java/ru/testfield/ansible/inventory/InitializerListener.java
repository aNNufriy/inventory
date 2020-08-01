package ru.testfield.ansible.inventory;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.testfield.ansible.inventory.model.LoginUser;
import ru.testfield.ansible.inventory.repository.LoginUserRepository;

import java.util.UUID;

@Component
public class InitializerListener {

    private final LoginUserRepository loginUserRepository;

    public InitializerListener(LoginUserRepository loginUserRepository) {
        this.loginUserRepository = loginUserRepository;
    }

    @EventListener({ContextRefreshedEvent.class})
    public void contextRefreshedEvent() {
        loginUserRepository.deleteAll();
        for(int i=0; i<100; i++) {
            LoginUser loginUser = new LoginUser(UUID.randomUUID(), "yyyy"+i, "password", null);
            loginUserRepository.save(loginUser);
        }
    }

}
