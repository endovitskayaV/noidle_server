package ru.vsu.noidle_server.model.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class LoginDto {
    private String name;
    private String password;
}
