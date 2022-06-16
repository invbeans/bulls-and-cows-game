package ru.invbeans.repository;

import org.springframework.data.repository.CrudRepository;
import ru.invbeans.model.domain.Player;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository extends CrudRepository<Player, Long> {
    Optional<Player> findByNickname(String nickname);
    Optional<Player> findByNicknameAndPassword(String nickname, String password);
}
