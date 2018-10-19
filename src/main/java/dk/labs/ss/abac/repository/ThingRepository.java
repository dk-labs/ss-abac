package dk.labs.ss.abac.repository;

import dk.labs.ss.abac.model.Thing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThingRepository extends JpaRepository<Thing, Long> {
}
