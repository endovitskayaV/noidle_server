package ru.vsu.noidle_server.model.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "email")
public class UserDto {

    private String email;

    private String name;

    private String photo;
}
