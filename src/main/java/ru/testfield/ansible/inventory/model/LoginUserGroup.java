package ru.testfield.ansible.inventory.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class LoginUserGroup {

    @Id
    private UUID id;

    private String groupName;

    @JsonIgnore
    @ManyToMany(mappedBy = "groups", fetch = FetchType.LAZY)
    private Set<LoginUser> loginUsers;

    @JsonIgnore
    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private Set<LoginUserGroup> children;

    @ManyToOne
    @JsonIdentityReference(alwaysAsId=true)
    private LoginUserGroup parent;

    @PreRemove
    private void removeGroupFromUsers() {
        for (LoginUser user : loginUsers) {
            user.getGroups().remove(this);
        }
        for (LoginUserGroup childGroup : children) {
            childGroup.parent = null;
        }
    }

}
