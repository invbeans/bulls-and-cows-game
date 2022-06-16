package ru.invbeans.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.invbeans.controller.AttemptController;
import ru.invbeans.model.domain.Attempt;
import ru.invbeans.model.dto.AttemptDto;
import ru.invbeans.service.AttemptService;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class AttemptControllerImpl implements AttemptController {;
    private final AttemptService service;

    @Override
    public ResponseEntity<Long> saveAttempt(AttemptDto dto){
        Long id = service.saveAttempt(dto);
        return ResponseEntity.ok(id);
    }

    @Override
    public ResponseEntity<List<Attempt>> getAttempts(){
        List<Attempt> attemptList = service.getAttempts();
        return ResponseEntity.ok(attemptList);
    }

    @Override
    public ResponseEntity<Attempt> getAttemptById(Long id){
        Optional<Attempt> attemptOptional = service.getAttemptById(id);
        if(attemptOptional.isPresent()){
            return ResponseEntity.ok(attemptOptional.get());
        }
        else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @Override
    public ResponseEntity<List<Attempt>> getByGameId(Long game){
        List<Attempt> attemptList = service.getByGameId(game);
        return ResponseEntity.ok(attemptList);
    }

}
