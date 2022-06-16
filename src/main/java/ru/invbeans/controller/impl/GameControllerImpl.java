package ru.invbeans.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.invbeans.controller.GameController;
import ru.invbeans.model.domain.Game;
import ru.invbeans.model.dto.GameDto;
import ru.invbeans.service.GameService;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class GameControllerImpl implements GameController {

    private final GameService service;

    @Override
    public ResponseEntity<Long> saveGame(GameDto dto){
        Long id = service.saveGame(dto);
        return ResponseEntity.ok(id);
    }

    @Override
    public ResponseEntity<List<Game>> getGames(){
        List<Game> gameList = service.getGames();
        return ResponseEntity.ok(gameList);
    }

    @Override
    public ResponseEntity<Game> getGameById(Long id){
        Optional<Game> gameOptional = service.getGameById(id);
        if(gameOptional.isPresent()){
            return ResponseEntity.ok(gameOptional.get());
        }
        else{
            return ResponseEntity.badRequest().body(null);
        }
    }

    @Override
    public ResponseEntity<List<Game>> findByPlayer(Long player){
        List<Game> gameList = service.findByPlayer(player);
        return ResponseEntity.ok(gameList);
    }
}
