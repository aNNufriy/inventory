package ru.testfield.ansible.inventory.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import ru.testfield.ansible.inventory.repository.LoginUserGroupRepository;

import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

@Component
public class LoginUserGroupFormatter implements Formatter<LoginUserGroup> {

    private final LoginUserGroupRepository loginUserGroupRepository;

    public LoginUserGroupFormatter(LoginUserGroupRepository loginUserGroupRepository) {
        this.loginUserGroupRepository = loginUserGroupRepository;
    }

    @Override
    public LoginUserGroup parse(String id, Locale locale) throws ParseException {
        Optional<LoginUserGroup> optionalLoginUserGroup = loginUserGroupRepository.findById(UUID.fromString(id));
        if(optionalLoginUserGroup.isPresent()) {
            return optionalLoginUserGroup.get();
        }else{
            throw new ParseException(id,0);
        }
    }

    @Override
    public String print(LoginUserGroup loginUserGroup, Locale locale) {
        return loginUserGroup.getId().toString();
    }
}
