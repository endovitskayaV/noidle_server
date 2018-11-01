package ru.vsu.noidle_server.model.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class UserDto {

    private UUID id;

    private String email;

    private String name;

    private String photo;
}
