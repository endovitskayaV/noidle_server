package ru.vsu.noidle_server.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class LanguageStatisticsDto {
    String languageName;
    String symbols;
    String time;
}