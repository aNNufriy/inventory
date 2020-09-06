package ru.testfield.ansible.model;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
public class AnsibleHostGroup {

    @Id
    @GeneratedValue
    private UUID id;

    @NotBlank
    @Size(min=2, max=100)
    @Column(unique = true)
    private String name;

    @JsonIdentityReference(alwaysAsId = true)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    @JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="name")
    private Set<AnsibleHostGroup> children;

    @JsonIdentityReference(alwaysAsId = true)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
    @JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="name")
    private Set<AnsibleHost> hosts;

    @ManyToOne
    @JsonIgnore
    private AnsibleHostGroup parent;

    @PreRemove
    private void removeGroupFromUsers() {
        for (AnsibleHostGroup childGroup : children) {
            childGroup.parent = null;
        }
        for (AnsibleHost host : hosts) {
            host.setGroup(null);
        }
    }
}
