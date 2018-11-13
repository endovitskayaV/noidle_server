package ru.vsu.noidle_server.model.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "user_data")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
@Proxy(lazy = false)
public class UserEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "photo")
    private String photo;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Collection<AchievementEntity> achievements;

    @ElementCollection
    @CollectionTable(name = "users_notifications", joinColumns = @JoinColumn(name="user_id"))
    @MapKeyJoinColumn(name = "notification_id")
    @Column(name="sent")
    private Map<NotificationEntity, Boolean> notificationSent;
}
