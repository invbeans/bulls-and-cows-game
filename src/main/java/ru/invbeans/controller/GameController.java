package ru.invbeans.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.invbeans.model.domain.Game;
import ru.invbeans.model.dto.GameDto;

import java.util.List;

@RequestMapping(GameController.MAPPING)
public interface GameController {
     String MAPPING = "/game";

     @PostMapping("/save")
     ResponseEntity<Long> saveGame(@RequestBody(required = false) GameDto dto);

     @GetMapping()
     ResponseEntity<List<Game>> getGames();

     @GetMapping("/{id}")
     ResponseEntity<Game> getGameById(@PathVariable Long id);

     @GetMapping("/player/{player}")
     ResponseEntity<List<Game>> findByPlayer(@PathVariable Long player);
}
