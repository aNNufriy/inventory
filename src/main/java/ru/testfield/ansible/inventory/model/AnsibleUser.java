package ru.testfield.ansible.inventory.model;

import lombok.*;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AnsibleUser {

    @Id
    private UUID uuid;

    private String username;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> rsaPublicKeys = new ArrayList<>();

}
