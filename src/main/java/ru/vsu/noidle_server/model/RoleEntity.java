package ru.vsu.noidle_server.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "role")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "code")
public class RoleEntity {

    @Id
    @Enumerated (value = EnumType.ORDINAL)
    @Column(name = "code")
    private Role code;

    @Column(name = "name", nullable = false)
    private String name;
}
