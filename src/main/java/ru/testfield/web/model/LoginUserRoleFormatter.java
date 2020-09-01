package ru.testfield.web.model;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import ru.testfield.web.repository.LoginUserRoleRepository;

import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

@Component
public class LoginUserRoleFormatter implements Formatter<LoginUserRole> {

    private final LoginUserRoleRepository loginUserRoleRepository;

    public LoginUserRoleFormatter(LoginUserRoleRepository loginUserRoleRepository) {
        this.loginUserRoleRepository = loginUserRoleRepository;
    }

    @Override
    public LoginUserRole parse(String id, Locale locale) throws ParseException {
        Optional<LoginUserRole> optionalLoginUserRole = loginUserRoleRepository.findById(UUID.fromString(id));
        if(optionalLoginUserRole.isPresent()) {
            return optionalLoginUserRole.get();
        }else{
            throw new ParseException(id,0);
        }
    }

    @Override
    public String print(LoginUserRole loginUserRole, Locale locale) {
        return loginUserRole.getId().toString();
    }
}