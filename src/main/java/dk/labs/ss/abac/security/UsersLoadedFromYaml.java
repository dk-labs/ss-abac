package dk.labs.ss.abac.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "users")
@Setter
@Getter
public class UsersLoadedFromYaml {

    private List<Map<String, String>> users = new ArrayList<>();
}
