package dk.labs.ss.abac.controller;

import dk.labs.ss.abac.model.Thing;
import dk.labs.ss.abac.repository.ThingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;

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
    @PreAuthorize("hasPermission(#thing, 'create')")
    public ResponseEntity<?> createAThing(@RequestBody Thing thing) {
        Thing persistedThing = this.thingRepository.save(thing);
        return ResponseEntity.ok(persistedThing);
    }

    @PutMapping("/{thingId}")
    @Transactional
    @PreAuthorize("hasPermission(#thingId, 'update')")
    public ResponseEntity<?> updateAThing(@PathVariable Long thingId, @RequestBody Thing modifiedThing) {
        Thing thingToUpdate = thingRepository.findById(thingId)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));

        thingToUpdate.setDescription(modifiedThing.getDescription());
        Thing persistedThing = thingRepository.save(thingToUpdate);
        return ResponseEntity.ok(persistedThing);
    }
}
