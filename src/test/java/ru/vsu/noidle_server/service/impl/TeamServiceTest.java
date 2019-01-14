package ru.vsu.noidle_server.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.vsu.noidle_server.exception.ServiceException;
import ru.vsu.noidle_server.model.domain.TeamEntity;
import ru.vsu.noidle_server.model.repository.TeamRepository;
import ru.vsu.noidle_server.service.TeamService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TeamServiceTest {

    @Autowired
    private TeamService teamService;

    @MockBean
    private TeamRepository teamRepository;

    private static final String NAME = "name";

    @Before
    public void setUp() {
        TeamEntity teamEntity = new TeamEntity(null, NAME, null, null, null);

        Mockito.when(teamRepository.getByName(teamEntity.getName()))
                .thenReturn(teamEntity);
    }

    @Test
    public void correctByName() throws ServiceException {
        teamService.getShortByName(NAME);
    }

}
