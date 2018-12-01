package ru.vsu.noidle_server.model.dto;

import lombok.*;

import java.util.Collection;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "email")
//@ToString(exclude = "statistics")
public class UserDto {

    private UUID id;

    private String email;

    private String name;

    private String photo;

    //private Collection<StatisticsDto> statistics;
}
