package ru.invbeans.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.invbeans.model.domain.Game;
import ru.invbeans.model.dto.GameDto;
import ru.invbeans.model.mapper.GameMapper;
import ru.invbeans.repository.GameRepository;
import ru.invbeans.service.GameService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {
    private final GameRepository repository;
    private final GameMapper mapper;

    @Override
    public Long saveGame(GameDto dto){
        Game game = mapper.toModel(dto);
        repository.save(game);
        return game.getId();
    }

    @Override
    public List<Game> getGames(){
        List<Game> gameList = (List<Game>) repository.findAll();
        return gameList;
    }

    @Override
    public Optional<Game> getGameById(Long id){
        Optional<Game> game = repository.findById(id);
        return game;
    }

    @Override
    public List<Game> findByPlayer(Long player){
        List<Game> gameList = repository.findByPlayer(player);
        return gameList;
    }
}
