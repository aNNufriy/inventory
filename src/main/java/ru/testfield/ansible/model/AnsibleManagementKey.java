package ru.testfield.ansible.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class AnsibleManagementKey {

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
    private String sshKey;

}