package ru.vsu.noidle_server.model.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Table(name = "user_data")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(of = {"id", "email", "name", "photo"})
public class UserEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "photo")
    private String photo;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Collection<StatisticsEntity> statistics;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_data_team",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "team_id", referencedColumnName = "id")
    )
    private Set<TeamEntity> teams;


    @OneToMany(mappedBy = "toWhomUser", cascade = CascadeType.ALL)
    private Set<NotificationEntity> ownNotifications;

    @OneToMany(mappedBy = "aboutUser", cascade = CascadeType.ALL)
    private Set<NotificationEntity> colleaguesNotifications;

    public UserEntity(String email, String name, String photo) {
        this.email = email;
        this.name = name;
        this.photo = photo;
    }


    public AchievementEntity getLevel() {
        return ownNotifications.stream()
                .filter(notificationEntity -> notificationEntity.getAchievement().isLevel() && notificationEntity.getAboutUser().equals(this))
                .max(Comparator.comparing(NotificationEntity::getAchievement))
                .map(NotificationEntity::getAchievement)
                .orElse(null);
    }

    public List<AchievementEntity> getAchievements() {
        return Stream.concat(
                Stream.of(getLevel()),
                ownNotifications.stream()
                        .filter(notificationEntity -> !notificationEntity.getAchievement().isLevel())
                        .map(NotificationEntity::getAchievement))
                .collect(Collectors.toList());
    }

    public void addOwnNotification(NotificationEntity notification) {
        ownNotifications.add(notification);
    }

    public Set<UserEntity> getColleagues() {
        Set<UserEntity> colleagues = new HashSet<>();
        teams.forEach(teamEntity ->
                colleagues.addAll(
                        teamEntity.getUsers().stream()
                                .filter(colleague -> !colleague.equals(this))
                                .collect(Collectors.toSet())
                )
        );

        return colleagues;
    }

    public void setOwnNotifications(Set<NotificationEntity> notifications) {
        if (notifications != null && !notifications.isEmpty()) {
            ownNotifications.addAll(notifications);
        }
    }

    public void addTeam(TeamEntity teamEntity){
        teams.add(teamEntity);
    }
}
