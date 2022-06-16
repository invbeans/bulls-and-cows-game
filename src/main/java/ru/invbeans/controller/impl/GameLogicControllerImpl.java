package ru.invbeans.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.invbeans.controller.GameLogicController;
import ru.invbeans.model.domain.Check;
import ru.invbeans.service.GameLogicService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequiredArgsConstructor
@ConfigurationProperties
public class GameLogicControllerImpl implements GameLogicController {
    private final GameLogicService service;

    @Value("${game.attempts}")
    private String[] attemptsLimit;

    @Value("${game.time}")
    private String[] timeLimit;

    @Override
    public ResponseEntity<Integer> generateNumber(){
        int number = service.generateNumber();
        return ResponseEntity.ok(number);
    }

    @Override
    public ResponseEntity<Check> checkAnswer(Integer answer, Integer guess){
        Check check = service.checkAnswer(answer, guess);
        return ResponseEntity.ok(check);
    }

    @Override
    public ResponseEntity<List<Integer>> getAttemptsLimit(){
        List<Integer> listAttempts = Stream.of(attemptsLimit).map(Integer::parseInt).collect(Collectors.toList());
        return ResponseEntity.ok(listAttempts);
    }

    @Override
    public  ResponseEntity<List<Integer>> getTimeLimit(){
        List<Integer> listTime = Stream.of(timeLimit).map(Integer::parseInt).collect(Collectors.toList());
        return ResponseEntity.ok(listTime);
    }

}
