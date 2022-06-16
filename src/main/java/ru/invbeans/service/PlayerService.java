package ru.invbeans.service;

import ru.invbeans.model.domain.Player;
import ru.invbeans.model.dto.PlayerDto;

import java.util.List;
import java.util.Optional;

public interface PlayerService {
    Long savePlayer(PlayerDto dto);
    List<Player> getPlayers();
    Optional<Player> findByNickname(String nickname);
    Optional<Player> findByNicknameAndPassword(String nickname, String password);
}
