package ru.invbeans.service;

import ru.invbeans.model.domain.Game;
import ru.invbeans.model.dto.GameDto;

import java.util.List;
import java.util.Optional;

public interface GameService {
    Long saveGame(GameDto dto);
    List<Game> getGames();
    Optional<Game> getGameById(Long id);
    List<Game> findByPlayer(Long player);
}
