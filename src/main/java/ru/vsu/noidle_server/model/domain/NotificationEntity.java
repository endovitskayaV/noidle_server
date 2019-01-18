package ru.vsu.noidle_server.model.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "notification")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id", "achievement", "aboutUser", "toWhomUser", "team"}) //not just id for new instances (id=null) correct comparison
@ToString
public class NotificationEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "about_user_id", referencedColumnName = "id")
    private UserEntity aboutUser;

    @ManyToOne
    @JoinColumn(name = "to_whom_user_id", referencedColumnName = "id")
    private UserEntity toWhomUser;

    @ManyToOne
    @JoinColumn(name = "achievement_id", referencedColumnName = "id")
    private AchievementEntity achievement;

    @ManyToOne
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    private TeamEntity team;

    @Column(name = "is_sent", nullable = false)
    private boolean isSent;

    @Column(name = "date", nullable = false)
    private OffsetDateTime date;

    public NotificationEntity(UserEntity aboutUser, UserEntity toWhomUser, AchievementEntity achievement, TeamEntity team, OffsetDateTime date) {
        setAboutUser(aboutUser);
        setAchievement(achievement);
        setTeam(team);
        setDate(date);
        setToWhomUser(toWhomUser);
        setSent(false);
    }

    public NotificationEntity(UserEntity aboutUser, AchievementEntity achievement, TeamEntity team, OffsetDateTime date) {
        this(aboutUser, aboutUser, achievement, team, date);
    }

    public NotificationEntity(UserEntity aboutUser, AchievementEntity achievement, OffsetDateTime date) {
        this(aboutUser, aboutUser, achievement, null, date);
    }

    public NotificationEntity(NotificationEntity notificationEntity) {
        this(
                notificationEntity.aboutUser,
                notificationEntity.toWhomUser,
                notificationEntity.achievement,
                notificationEntity.team,
                notificationEntity.date
        );
    }
}
