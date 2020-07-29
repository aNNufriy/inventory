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
public class LoginUserGroup {

    @Id
    private UUID uuid;

    private String groupName;

    @ManyToMany(mappedBy = "groups", fetch = FetchType.EAGER)
    private Set<LoginUser> loginUsers;

}
