package ru.vsu.noidle_server.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "email")
@ToString
public class UserDtoForNotification {
    private String email;

    private String name;
}
