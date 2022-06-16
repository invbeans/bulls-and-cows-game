package ru.invbeans.service;

import ru.invbeans.model.domain.Attempt;
import ru.invbeans.model.dto.AttemptDto;

import java.util.List;
import java.util.Optional;

public interface AttemptService {
    Long saveAttempt(AttemptDto dto);
    List<Attempt> getAttempts();
    Optional<Attempt> getAttemptById(Long id);

    List<Attempt> getByGameId(Long game);
}
