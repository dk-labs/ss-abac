package dk.labs.ss.abac.security;

import dk.labs.ss.abac.model.Thing;
import dk.labs.ss.abac.repository.ThingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.client.HttpClientErrorException;

import java.io.Serializable;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class CustomPermissionEvaluator implements PermissionEvaluator {

    private ThingRepository thingRepository;

    public CustomPermissionEvaluator(ThingRepository thingRepository) {
        this.thingRepository = thingRepository;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        boolean hasPermission = false;
        if (targetDomainObject != null) {
            if (Thing.class.isAssignableFrom(targetDomainObject.getClass())) {
                Thing thing = Thing.class.cast(targetDomainObject);
                switch (TARGET_ACTION.valueOf(permission.toString())) {
                    //Demonstrates that only admins could create things
                    case create:
                        hasPermission = authentication.getAuthorities().stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(toList())
                                .contains("ROLE_ADMIN");
                        break;
                    default:
                        break;
                }
            } else if (Long.class.isAssignableFrom(targetDomainObject.getClass())) {
                Thing thing = thingRepository.findById(Long.class.cast(targetDomainObject))
                        .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));

                switch (TARGET_ACTION.valueOf(permission.toString())) {
                    //Demonstrates that the ONLY users who created entities could update those entities. Utilizes Spring Data's
                    //audit field to query for principals who own persisted entities in question
                    case update:
                        hasPermission = User.class.cast(authentication.getPrincipal()).getUsername().equals(thing.getCreatedBy());
                    default:
                        break;
                }
            }
        }

        return hasPermission;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }

    public enum TARGET_ACTION {
        create, update, fetch_one, fetch_all
    }
}
