package ru.vsu.noidle_server.model.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "level")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "order")
@ToString
public class LevelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order", nullable = false)
    private Long order;

    @Column(name = "name", nullable = false)
    private String name;
}