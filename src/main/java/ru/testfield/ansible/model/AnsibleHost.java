package ru.testfield.ansible.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.testfield.web.model.LoginUserRole;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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
    private String hostName;

    @NotBlank
    @Size(min=2, max=100)
    @Column(unique = true)
    private String address;

}
