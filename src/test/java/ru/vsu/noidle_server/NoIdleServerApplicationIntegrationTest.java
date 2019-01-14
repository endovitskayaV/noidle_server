package ru.vsu.noidle_server;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import ru.vsu.noidle_server.model.repository.TeamRepositoryTest;
import ru.vsu.noidle_server.service.impl.TeamServiceIntegrationTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TeamRepositoryTest.class,
        TeamRepositoryTest.class,
        TeamServiceIntegrationTest.class
})
public class NoIdleServerApplicationIntegrationTest {
}
