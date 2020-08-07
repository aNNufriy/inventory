package ru.testfield.ansible.inventory.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class LoginUser {

    @Id
    @GeneratedValue
    private UUID id;

    private String login;

    private String passwordBcryptHash;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<LoginUserGroup> groups;

}
