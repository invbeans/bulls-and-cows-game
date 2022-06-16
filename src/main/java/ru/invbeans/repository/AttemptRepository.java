package ru.invbeans.repository;

import org.springframework.data.repository.CrudRepository;
import ru.invbeans.model.domain.Attempt;

import java.util.List;

public interface AttemptRepository extends CrudRepository<Attempt, Long> {
    List<Attempt> findByGame(Long game);
}
