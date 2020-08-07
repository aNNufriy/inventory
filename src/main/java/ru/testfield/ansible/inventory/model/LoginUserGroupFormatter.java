package ru.testfield.ansible.inventory.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import ru.testfield.ansible.inventory.repository.LoginUserGroupRepository;

import java.text.ParseException;
import java.util.Locale;
import java.util.UUID;

@Component
public class LoginUserGroupFormatter implements Formatter<LoginUserGroup> {

    @Autowired
    private LoginUserGroupRepository loginUserGroupRepository;

    @Override
    public LoginUserGroup parse(String id, Locale locale) throws ParseException {
        LoginUserGroup loginUserGroup = new LoginUserGroup();
        loginUserGroup.setId(UUID.fromString(id));
        return loginUserGroup;
    }

    @Override
    public String print(LoginUserGroup loginUserGroup, Locale locale) {
        return loginUserGroup.getId().toString();
    }
}
