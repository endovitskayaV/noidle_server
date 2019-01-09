package ru.vsu.noidle_server.model.dto;

import lombok.*;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class LanguageStatisticsDto implements Comparable<LanguageStatisticsDto> {
    String languageName;
    String symbols;
    String time;

    @Override
    public int compareTo(@NotNull LanguageStatisticsDto o) {
        return this.symbols.compareTo(o.getSymbols());
    }
}
