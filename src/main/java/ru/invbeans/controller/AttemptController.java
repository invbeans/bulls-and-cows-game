package ru.invbeans.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.invbeans.model.domain.Attempt;
import ru.invbeans.model.dto.AttemptDto;

import java.util.List;

@RequestMapping(AttemptController.MAPPING)
public interface AttemptController {
    String MAPPING = "/attempt";

    @PostMapping("/save")
    ResponseEntity<Long> saveAttempt(@RequestBody(required = false) AttemptDto dto);

    @GetMapping()
    ResponseEntity<List<Attempt>> getAttempts();

    @GetMapping("/{id}")
    ResponseEntity<Attempt> getAttemptById(@PathVariable Long id);

    @GetMapping("/game/{game}")
    ResponseEntity<List<Attempt>> getByGameId(@PathVariable Long game);
}
