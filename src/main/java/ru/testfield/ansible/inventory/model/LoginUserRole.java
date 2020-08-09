package ru.testfield.ansible.inventory.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class LoginUserRole {

    @Id
    @GeneratedValue
    private UUID id;

    @NotBlank
    @Size(min=2, max=30)
    @Column(unique = true)
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Set<LoginUser> loginUsers;

    @JsonIgnore
    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private Set<LoginUserRole> children;

    @ManyToOne
    @JsonIdentityReference(alwaysAsId=true)
    private LoginUserRole parent;

    @PreRemove
    private void removeRoleFromUsers() {
        for (LoginUser user : loginUsers) {
            user.getRoles().remove(this);
        }
        for (LoginUserRole childRole : children) {
            childRole.parent = null;
        }
    }

}
