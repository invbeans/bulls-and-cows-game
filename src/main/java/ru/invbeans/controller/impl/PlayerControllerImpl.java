package ru.invbeans.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.invbeans.controller.PlayerController;
import ru.invbeans.model.domain.Player;
import ru.invbeans.model.dto.PlayerDto;
import ru.invbeans.service.PlayerService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class PlayerControllerImpl implements PlayerController {
    private final PlayerService service;

    @Override
    public ResponseEntity<Long> savePlayer(PlayerDto dto){
        Long id = service.savePlayer(dto);
        return ResponseEntity.ok(id);
    }

    @Override
    public ResponseEntity<List<Player>> getPlayers(){
        List<Player> playerList = service.getPlayers();
        return ResponseEntity.ok(playerList);
    }

    @Override
    public ResponseEntity<Long> findByNickname(String nickname){
        //поиск по полю никнейм для авторизации
        Optional<Player> playerOptional = service.findByNickname(nickname);
        if(playerOptional.isPresent()){
            //пользователь уже имеется в базе, возвращается его id
            Long id = playerOptional.get().getId();
            return ResponseEntity.ok(id);
        }
        else{
            //новый пользователь, возвращается 0
            return ResponseEntity.ok(0L);
        }
    }

    @Override
    public ResponseEntity<Long> findByNicknameAndPassword(String nickname, String password){
        //поиск по полям никнейм и пароль, для проверки правильности пароля
        Optional<Player> playerOptional = service.findByNicknameAndPassword(nickname, password);
        if(playerOptional.isPresent()){
            //если пользователь с этими полями найден, возвращается его id
            Long id = playerOptional.get().getId();
            return ResponseEntity.ok(id);
        }
        else{
            //пароль не совпал, возвращается 0
            return ResponseEntity.ok(0L);
        }
    }
}
