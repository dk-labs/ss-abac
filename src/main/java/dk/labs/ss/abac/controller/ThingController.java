package dk.labs.ss.abac.controller;

import dk.labs.ss.abac.model.Thing;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/things")
public class ThingController {

    @GetMapping
    public ResponseEntity<?> getAllThings() {
        return ResponseEntity.ok(new ArrayList<Thing>());
    }
}
