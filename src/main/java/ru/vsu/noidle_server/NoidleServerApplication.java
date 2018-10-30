package ru.vsu.noidle_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.flywaydb.core.Flyway;

@SpringBootApplication
public class NoidleServerApplication {

	public static void main(String[] args) {
        Flyway flyway = new Flyway();
        flyway.setDataSource(System.getenv("JDBC_DATABASE_URL"),
                System.getenv("JDBC_DATABASE_USERNAME"),
                System.getenv("JDBC_DATABASE_PASSWORD"));
        flyway.migrate();
		SpringApplication.run(NoidleServerApplication.class, args);
	}
}
