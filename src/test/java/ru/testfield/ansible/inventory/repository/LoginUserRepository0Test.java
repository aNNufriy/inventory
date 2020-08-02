package ru.testfield.ansible.inventory.repository;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.testfield.ansible.inventory.model.LoginUser;
import ru.testfield.ansible.inventory.model.LoginUserGroup;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LoginUserRepository0Test {

    @Autowired
    private LoginUserRepository0 loginUserRepository0;

    @Autowired
    private LoginGroupRepository loginGroupRepository;

    @Test
    @Order(1)
    public void deleteAll(){
        loginUserRepository0.deleteAll();
        loginGroupRepository.deleteAll();
    }

    @Test
    @Order(2)
    public void save(){
        Set<LoginUserGroup> loginUserGroups = new HashSet<>();
        loginUserGroups.add(new LoginUserGroup(UUID.randomUUID(),"group-1",null));
        loginUserGroups.add(new LoginUserGroup(UUID.randomUUID(),"group-12",null));

        LoginUser loginUser = new LoginUser(UUID.randomUUID(),"user-1", "password", loginUserGroups);

        Set<LoginUserGroup> loginUserGroups2 = new HashSet<>();
        loginUserGroups2.add(new LoginUserGroup(UUID.randomUUID(),"group-2",null));
        loginUserGroups2.add(new LoginUserGroup(UUID.randomUUID(),"group-22",null));

        LoginUser loginUser2 = new LoginUser(UUID.randomUUID(),"user-2", "password", loginUserGroups2);

        loginUserRepository0.persitLoginUser(loginUser);
        loginUserRepository0.persitLoginUser(loginUser2);
    }

    @Test
    @Order(3)
    public void count(){
        assertEquals(2, loginUserRepository0.count());
        assertEquals(4, loginGroupRepository.count());
    }

    @Test
    @Order(4)
    public void  findByGroup() {
        findUserByGroup(1);
        findUserByGroup(2);
    }

    @Test
    @Order(5)
    public void delete() {
        LoginUser loginUser = loginUserRepository0.findByLogin("user-1");
        loginUserRepository0.deleteById(loginUser.getId());
        assertEquals(1, loginUserRepository0.count());
        assertEquals(4, loginGroupRepository.count());
    }

    private void findUserByGroup(int number) {
        List<LoginUser> byGroup2 = loginUserRepository0.findByGroup("group-"+number);
        assertEquals(byGroup2.size(), 1);
        assertEquals(byGroup2.get(0).getLogin(), "name-"+number);
    }

}