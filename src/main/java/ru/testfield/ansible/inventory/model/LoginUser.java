package ru.testfield.ansible.inventory.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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

    @NotBlank
    @Size(min=2, max=30)
    @Column(unique = true)
    private String login;

    private String passwordBcryptHash;

    private boolean enabled;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<LoginUserRole> loginUserRoles;

}
