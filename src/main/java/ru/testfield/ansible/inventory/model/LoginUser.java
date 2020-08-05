package ru.testfield.ansible.inventory.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LoginUser {

    @Id
    private UUID id;

    private String login;

    private String passwordBcryptHash;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<LoginUserGroup> groups;

}
