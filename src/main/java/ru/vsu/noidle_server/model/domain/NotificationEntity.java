package ru.vsu.noidle_server.model.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "notification")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
@Proxy(lazy = false)
public class NotificationEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "achievement_id",  referencedColumnName = "id")
    private AchievementEntity achievement;

    @Column(name = "is_sent", nullable = false)
    private boolean isSent;

    @Column(name = "date", nullable = false)
    private OffsetDateTime date;

    public NotificationEntity(UserEntity user, AchievementEntity achievement, OffsetDateTime date) {
        this.user = user;
        this.achievement = achievement;
        this.isSent = false;
        this.date = date;
    }
}
