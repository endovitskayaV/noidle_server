package ru.vsu.noidle_server.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "order")
@ToString
public class LevelDto {

    private Long order;

    private String name;
}
