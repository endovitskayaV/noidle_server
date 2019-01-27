package ru.vsu.noidle_server.model.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import ru.vsu.noidle_server.model.UserRole;

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

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "photo")
    private String photo;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Collection<StatisticsEntity> statistics;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UserTeam> usersTeams;

    @OneToMany(mappedBy = "toWhomUser", cascade = CascadeType.ALL)
    private Set<NotificationEntity> allNotifications;

    private void setAllNotifications(Set<NotificationEntity> allNotifications) {
        this.allNotifications = allNotifications;
    }

    public void addNotification(NotificationEntity notification) {
        if (notification != null) {
            allNotifications.add(notification);
            if (notification.getAboutUser().equals(this)) {
                personalNotifications.add(notification);
            }
        }
    }

    public boolean hasAchievement(Long achievementId) {
        return allNotifications.stream()
                .anyMatch(notification -> notification.getAchievement().getId().equals(achievementId));
    }

    /**
     * @param achievementId
     * @return teams in which user has  achievement with specified id
     */
    public List<TeamEntity> getTeamsByAchievementId(Long achievementId) {
        return hasAchievement(achievementId) ?
                allNotifications.stream()
                        .filter(notification -> notification.getAchievement().getId().equals(achievementId))
                        .map(NotificationEntity::getTeam)
                        .collect(Collectors.toList()) :
                Collections.emptyList();
    }

    @OneToMany(mappedBy = "aboutUser", cascade = CascadeType.ALL)
    private Set<NotificationEntity> personalNotifications;

    private Set<NotificationEntity> getPersonalNotifications() {
        return personalNotifications;
    }

    private void setPersonalNotifications(Set<NotificationEntity> personalNotifications) {
        this.personalNotifications = personalNotifications;
    }

    public UserEntity(String email, String name, String photo) {
        this.email = email;
        this.name = name;
        this.photo = photo;
    }

    public AchievementEntity getLevel() {
        return personalNotifications.stream()
                .filter(notificationEntity -> notificationEntity.getAchievement().isLevel() && notificationEntity.getAboutUser().equals(this))
                .max(Comparator.comparing(NotificationEntity::getAchievement))
                .map(NotificationEntity::getAchievement)
                .orElse(null);
    }

    public Set<AchievementEntity> getNonLevelAchievements() {
        return Stream.concat(
                Stream.of(getLevel()),
                personalNotifications.stream()
                        .filter(notificationEntity -> !notificationEntity.getAchievement().isLevel())
                        .map(NotificationEntity::getAchievement))
                .collect(Collectors.toSet());
    }

    public boolean hasTeamAchievement(UUID teamId, Long achievementId) {
        if (teamId == null || achievementId == null) {
            return false;
        }
        return allNotifications.stream()
                .filter(notification -> notification.getTeam() != null)
                .anyMatch(notification ->
                        notification.getTeam().getId().equals(teamId)
                                && notification.getAchievement().getId().equals(achievementId)
                                && notification.getAboutUser().getId().equals(id));
    }

    public Set<UserEntity> getColleagues() {
        Set<UserEntity> colleagues = new HashSet<>();
        getTeams().forEach(teamEntity ->
                colleagues.addAll(
                        teamEntity.getUsers().stream()
                                .filter(colleague -> !colleague.equals(this))
                                .collect(Collectors.toSet())
                )
        );

        return colleagues;
    }


//    public Set<UserEntity> getColleagues() {
//        Set<UserEntity> colleagues = new HashSet<>();
//        usersTeams.forEach(userTeam ->
//                colleagues.addAll(
//                        userTeam.getTeam().getUsersTeams().stream()
//                                .filter(userTeam1 -> userTeam1.getUser().equals(this))
//                                .map(UserTeam::getUser)
//                                .collect(Collectors.toSet())
//                )
//        );
//
//        return colleagues;
//    }

    public Set<TeamEntity> getTeams(){
        return usersTeams.stream().map(UserTeam::getTeam).collect(Collectors.toSet());
    }

    public void addTeam(TeamEntity teamEntity) {
       usersTeams.add(new UserTeam(teamEntity,this, UserRole.LEAD));
    }

    public void removeTeam(TeamEntity teamEntity) {
        usersTeams.remove(new UserTeam(teamEntity,this));
    }

    public boolean isTeammateWith(UserEntity teammember) {
        return getTeams().stream().anyMatch(team ->
                team.getUsers().stream()
                        .anyMatch(userEntity ->
                                userEntity.getId().equals(teammember.getId())
                        )
        );
    }
}
