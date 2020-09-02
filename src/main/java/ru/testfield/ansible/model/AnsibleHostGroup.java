package ru.testfield.ansible.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class AnsibleHostGroup {

    @Id
    @GeneratedValue
    private UUID id;

    @NotBlank
    @Size(min=2, max=100)
    @Column(unique = true)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private Set<AnsibleHostGroup> children;

    @ManyToOne
    @JsonIdentityReference(alwaysAsId=true)
    private AnsibleHostGroup parent;

    @PreRemove
    private void removeGroupFromUsers() {
        for (AnsibleHostGroup childGroup : children) {
            childGroup.parent = null;
        }
    }
}
