package ru.invbeans.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.invbeans.model.domain.Player;
import ru.invbeans.model.dto.PlayerDto;

import java.util.List;

@RequestMapping(PlayerController.MAPPING)
public interface PlayerController {
    String MAPPING = "/player";

    @PostMapping("/save")
    ResponseEntity<Long> savePlayer(@RequestBody(required = false) PlayerDto dto);

    @GetMapping()
    ResponseEntity<List<Player>> getPlayers();

    @GetMapping("/nickname")
    ResponseEntity<Long> findByNickname(@RequestParam String nickname);

    @GetMapping("/nick_pass")
    ResponseEntity<Long> findByNicknameAndPassword(@RequestParam String nickname, @RequestParam String password);

}
