package ru.vsu.noidle_server.model.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.vsu.noidle_server.model.domain.TeamEntity;

import javax.persistence.PersistenceException;
import java.time.OffsetDateTime;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TeamRepositoryTest {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void correctSave() {

        for (int i = 0; i < 5; i++) {
            final TeamEntity teamEntity = new TeamEntity();
            teamEntity.setName("name" + i);
            teamEntity.setCreated(OffsetDateTime.now());

            final TeamEntity teamDb = testEntityManager.persistAndFlush(teamEntity);

            assertNotNull(teamDb);
            assertNotNull(teamDb.getId());
            assertNotNull(teamDb.getName());
            assertEquals(teamEntity.getId(), teamDb.getId());
            assertEquals(teamEntity.getName(), teamDb.getName());
            assertEquals(teamEntity.getCreated(), teamDb.getCreated());
            assertNull(teamDb.getPhoto());
        }
    }


    @Test(expected = PersistenceException.class)
    public void incorrectSaveSameId() {
        final TeamEntity teamDb = testEntityManager.persistAndFlush(
                new TeamEntity(null, "name", "photo", OffsetDateTime.now(), null));

        final TeamEntity teamEntitySameId = new TeamEntity(teamDb.getId(), "name2", null, null, null);
        testEntityManager.persistAndFlush(teamEntitySameId);
    }

    @Test(expected = PersistenceException.class)
    public void incorrectSaveSameName() {
        final TeamEntity teamDb = testEntityManager.persistAndFlush(
                new TeamEntity(null, "name", "photo", OffsetDateTime.now(), null));

        final TeamEntity teamEntitySameName = new TeamEntity(null, teamDb.getName(), null, null, null);
        testEntityManager.persistAndFlush(teamEntitySameName);
    }

    @Test
    public void correctGet() {

        for (int i = 0; i < 5; i++) {

            final TeamEntity teamDb = testEntityManager.persistAndFlush(
                    new TeamEntity(null, "name" + i, null, OffsetDateTime.now(), null));

            final TeamEntity teamEntityByName = teamRepository.getByName(teamDb.getName());

            assertNotNull(teamEntityByName.getName());
            assertEquals(teamEntityByName.getId(), teamDb.getId());
            assertEquals(teamEntityByName.getName(), teamDb.getName());
            assertEquals(teamEntityByName.getCreated(), teamDb.getCreated());
            assertNull(teamDb.getPhoto());

            final TeamEntity teamEntityById = teamRepository.getOne(teamDb.getId());

            assertNotNull(teamEntityById.getName());
            assertEquals(teamEntityById.getId(), teamDb.getId());
            assertEquals(teamEntityById.getName(), teamDb.getName());
            assertEquals(teamEntityById.getCreated(), teamDb.getCreated());
            assertNull(teamDb.getPhoto());
        }
    }
}
