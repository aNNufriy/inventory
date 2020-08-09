package ru.testfield.ansible.inventory;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.testfield.ansible.inventory.model.LoginUser;
import ru.testfield.ansible.inventory.model.LoginUserRole;
import ru.testfield.ansible.inventory.repository.LoginUserRoleRepository;
import ru.testfield.ansible.inventory.repository.LoginUserRepository;

import java.util.Set;
import java.util.UUID;

@Component
public class InitializerListener {

    private final LoginUserRepository loginUserRepository;

    private final LoginUserRoleRepository loginUserRoleRepository;

    public InitializerListener(LoginUserRepository loginUserRepository, LoginUserRoleRepository loginUserRoleRepository) {
        this.loginUserRepository = loginUserRepository;
        this.loginUserRoleRepository = loginUserRoleRepository;
    }

    @EventListener({ContextRefreshedEvent.class})
    public void contextRefreshedEvent() {
        loginUserRepository.deleteAll();
        loginUserRoleRepository.deleteAll();

        loginUserRepository.save(new LoginUser(UUID.randomUUID(),"admin","$2y$12$jcL1Fzrrt.NXVIfV2WuKSuLGohR.gCmsVnIos1ri/JUcYk6mifro.",true, null));

        LoginUserRole loginUserRole1 = loginUserRoleRepository.save(
                new LoginUserRole(UUID.randomUUID(),"role1", null,null,null)
        );
        LoginUserRole loginUserRole2 = loginUserRoleRepository.save(
                new LoginUserRole(UUID.randomUUID(),"role2", null, null, loginUserRole1)
        );
        LoginUserRole loginUserRole3 = loginUserRoleRepository.save(
                new LoginUserRole(UUID.randomUUID(),"role3", null, null, loginUserRole1)
        );
        LoginUserRole loginUserRole4 = loginUserRoleRepository.save(
                new LoginUserRole(UUID.randomUUID(),"role4", null, null, loginUserRole1)
        );

        loginUserRoleRepository.save(loginUserRole1);
        loginUserRoleRepository.save(loginUserRole2);
        loginUserRoleRepository.save(loginUserRole3);
        loginUserRoleRepository.save(loginUserRole4);

        var roles = Set.of(loginUserRole1, loginUserRole2);

        for(int i=0; i<20; i++) {
            LoginUser loginUser = new LoginUser(UUID.randomUUID(), "user"+i, "password", true, roles);
            loginUserRepository.save(loginUser);
        }

    }

}
