package ru.testfield.ansible.model;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class AnsibleHost {

    @Id
    @GeneratedValue
    private UUID id;

    @NotBlank
    @Size(min=2, max=100)
    @Column(unique = true)
    private String name;

    @ManyToOne
    @JsonIdentityReference(alwaysAsId = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="name")
    private AnsibleHostGroup group;

    @ElementCollection
    @JoinTable(name="ansible_host_variables", joinColumns=@JoinColumn(name="ansible_host_id"))
    @MapKeyColumn(name="variable_name")
    @Column(name="variable_value")
    private Map<String, String> variables = new TreeMap<>();

}
