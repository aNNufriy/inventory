package ru.testfield.web.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
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
    @ManyToMany(mappedBy = "loginUserRoles", fetch = FetchType.LAZY)
    private Set<LoginUser> loginUsers;

    @PreRemove
    private void removeRoleFromUsers() {
        for (LoginUser user : loginUsers) {
            user.getLoginUserRoles().remove(this);
        }
    }

}
