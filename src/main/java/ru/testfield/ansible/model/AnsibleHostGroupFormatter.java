package ru.testfield.ansible.model;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import ru.testfield.ansible.repository.AnsibleHostGroupRepository;

import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

@Component
public class AnsibleHostGroupFormatter implements Formatter<AnsibleHostGroup> {

    private final AnsibleHostGroupRepository ansibleHostGroupRepository;

    public AnsibleHostGroupFormatter(AnsibleHostGroupRepository ansibleHostGroupRepository) {
        this.ansibleHostGroupRepository = ansibleHostGroupRepository;
    }

    @Override
    public AnsibleHostGroup parse(String id, Locale locale) throws ParseException {
        Optional<AnsibleHostGroup> optionalLoginUserGroup = ansibleHostGroupRepository.findById(UUID.fromString(id));
        if(optionalLoginUserGroup.isPresent()) {
            return optionalLoginUserGroup.get();
        }else{
            throw new ParseException(id,0);
        }
    }

    @Override
    public String print(AnsibleHostGroup ansibleHostGroup, Locale locale) {
        return ansibleHostGroup.getId().toString();
    }
}
