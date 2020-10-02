package ru.testfield.ansible.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class AnsibleTask {

    @Id
    @GeneratedValue
    private UUID id;

    @NotBlank
    @Size(min=2, max=100)
    @Column(unique = true)
    private String name;

    @NotBlank
    @Size(min=2, max=100)
    @Column(unique = true)
    private String body;

}
