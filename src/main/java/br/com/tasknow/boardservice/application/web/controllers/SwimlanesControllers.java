package br.com.tasknow.boardservice.application.web.controllers;

import br.com.tasknow.boardservice.domain.entities.Swimlane;
import br.com.tasknow.boardservice.domain.service.SwimlaneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/swimlanes")
public class SwimlanesControllers {

    private final SwimlaneService swimlaneService;

    @GetMapping
    public ResponseEntity<List<Swimlane>> getAll() {
        return ResponseEntity.ok(swimlaneService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Swimlane> getById(@PathVariable Long id) {
        return ResponseEntity.ok(swimlaneService.getById(id));
    }

    @PostMapping()
    public ResponseEntity<Swimlane> save(@RequestBody Swimlane swimlane) {
        return ResponseEntity.ok(swimlaneService.save(swimlane));
    }
}
