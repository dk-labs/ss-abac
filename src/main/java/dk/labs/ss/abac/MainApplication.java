package dk.labs.ss.abac;

import dk.labs.ss.abac.security.UsersLoadedFromYaml;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableConfigurationProperties({UsersLoadedFromYaml.class})
@EnableJpaRepositories
@EnableJpaAuditing
public class MainApplication {

    public static void main(String... args) {
        SpringApplication.run(MainApplication.class, args);
    }
}
