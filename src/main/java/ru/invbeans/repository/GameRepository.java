package ru.invbeans.repository;

import org.springframework.data.repository.CrudRepository;
import ru.invbeans.model.domain.Game;

import java.util.List;

public interface GameRepository extends CrudRepository<Game, Long> {
    List<Game> findByPlayer(Long player);
}
