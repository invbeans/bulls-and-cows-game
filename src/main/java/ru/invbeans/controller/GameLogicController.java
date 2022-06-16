package ru.invbeans.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.invbeans.model.domain.Check;

import java.util.List;

@RequestMapping(GameLogicController.MAPPING)
public interface GameLogicController {
    String MAPPING = "/logic";

    @GetMapping("/generate")
    ResponseEntity<Integer> generateNumber();

    @GetMapping("/check")
    ResponseEntity<Check> checkAnswer(@RequestParam Integer answer,
                                        @RequestParam Integer guess);

    @GetMapping("/attempts_limit")
    ResponseEntity<List<Integer>> getAttemptsLimit();

    @GetMapping("/time_limit")
    ResponseEntity<List<Integer>> getTimeLimit();
}
