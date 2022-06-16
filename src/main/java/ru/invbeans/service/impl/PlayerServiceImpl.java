package ru.invbeans.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.invbeans.model.domain.Player;
import ru.invbeans.model.dto.PlayerDto;
import ru.invbeans.model.mapper.PlayerMapper;
import ru.invbeans.repository.PlayerRepository;
import ru.invbeans.service.PlayerService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository repository;
    private final PlayerMapper mapper;

    @Override
    public Long savePlayer(PlayerDto dto) {
        Player player = mapper.toModel(dto);
        repository.save(player);
        return player.getId();
    }

    @Override
    public List<Player> getPlayers(){
        List<Player> playerList = (List<Player>) repository.findAll();
        return playerList;
    }

    @Override
    public Optional<Player> findByNickname(String nickname){
        Optional<Player> player = repository.findByNickname(nickname);
        return player;
    }

    @Override
    public Optional<Player> findByNicknameAndPassword(String nickname, String password){
        Optional<Player> player = repository.findByNicknameAndPassword(nickname, password);
        return player;
    }
}
