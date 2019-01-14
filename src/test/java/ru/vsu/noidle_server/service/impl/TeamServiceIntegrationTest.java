package ru.vsu.noidle_server.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.vsu.noidle_server.exception.ServiceException;
import ru.vsu.noidle_server.model.domain.TeamEntity;
import ru.vsu.noidle_server.model.dto.TeamDtoShort;
import ru.vsu.noidle_server.model.repository.TeamRepository;
import ru.vsu.noidle_server.service.TeamService;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TeamServiceIntegrationTest {

    @Autowired
    private TeamService teamService;

    @Autowired
    private TeamRepository teamRepository;

    @Test
    public void correctByName() {
        String name = "name";
        teamRepository.save(new TeamEntity(null, name, null, null, null));
        TeamDtoShort team = null;
        try {
            team = teamService.getShortByName(name);
            assertEquals(name, team.getName());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void correctById() {
        UUID id = teamRepository.save(
                new TeamEntity(null, "", null, null, null)).getId();
        TeamDtoShort team = null;
        try {
            team = teamService.getShortById(id);
            assertEquals(id, team.getId());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = ServiceException.class)
    public void incorrectById() throws ServiceException {
        UUID id = new UUID(0L, 0L);
        TeamEntity teamEntity = teamRepository.findById(id).orElse(null);
        if (teamEntity != null) {
            throw new ServiceException("");
        } else {
            teamService.getShortById(id);
        }
    }

    @Test(expected = ServiceException.class)
    public void incorrectByName() throws ServiceException {
        String name = "";
        TeamEntity teamEntity = teamRepository.getByName("");
        if (teamEntity != null) {
            throw new ServiceException(name);
        } else {
            teamService.getShortByName(name);
        }
    }
}
