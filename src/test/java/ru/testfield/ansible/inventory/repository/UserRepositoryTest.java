package ru.testfield.ansible.inventory.repository;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.testfield.ansible.inventory.model.AnsibleUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Order(1)
    public void deleteAll(){
        userRepository.deleteAll();
    }

    @Test
    @Order(2)
    public void save(){
        AnsibleUser ansibleUser = getAnsibleUser(List.of("key1", "key2", "key3"));
        AnsibleUser user = userRepository.save(ansibleUser);
        assertNotNull(user);
        assertNotNull(user.getUuid());
    }

    @Test
    @Order(3)
    public void saveAll(){
        userRepository.saveAll(IntStream.range(1,11)
                .mapToObj(i->getAnsibleUser(new ArrayList<>()))
                .collect(Collectors.toList())
        );
    }

    @Test
    @Order(4)
    public void findAll(){
//        List<AnsibleUser> all = userRepository.findAll();
//        assertNotNull(all);
//        assertEquals(11, all.size());
    }

    @Test
    @Order(5)
    public void count(){
        long count = userRepository.count();
        assertEquals(count,11);
    }

    private AnsibleUser getAnsibleUser(List<String> keys) {
        var au = new AnsibleUser();
        au.setUuid(UUID.randomUUID());
        au.setUsername("user" + new Random().nextInt());
        au.setRsaPublicKeys(keys);
        return au;
    }

}