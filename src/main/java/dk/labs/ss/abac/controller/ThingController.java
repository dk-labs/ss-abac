package dk.labs.ss.abac.controller;

import dk.labs.ss.abac.model.Thing;
import dk.labs.ss.abac.repository.ThingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/things")
public class ThingController {

    private ThingRepository thingRepository;

    public ThingController(ThingRepository thingRepository) {
        this.thingRepository = thingRepository;
    }

    @GetMapping
    public ResponseEntity<?> getAllThings() {
        return ResponseEntity.ok(this.thingRepository.findAll());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> createAThing(@RequestBody Thing thing) {
        Thing persistedThing = this.thingRepository.save(thing);
        return ResponseEntity.ok(persistedThing);
    }
}
