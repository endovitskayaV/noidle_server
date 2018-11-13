package ru.vsu.noidle_server.model.domain;

import lombok.*;
import ru.vsu.noidle_server.model.SubType;
import ru.vsu.noidle_server.model.Type;
import ru.vsu.noidle_server.model.converter.SubTypeConverter;
import ru.vsu.noidle_server.model.converter.TypeConverter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "notification")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class NotificationEntity {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Convert(converter = TypeConverter.class)
    @Column(name = "type")
    private Type type;

    @Convert(converter = SubTypeConverter.class)
    @Column(name = "subType")
    private SubType subType;

    @Column(name = "level", nullable = false)
    private Integer level;

    @Column(name = "value", nullable = false)
    private Long value;

}
