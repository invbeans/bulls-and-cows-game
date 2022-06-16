package ru.invbeans.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.invbeans.model.domain.Attempt;
import ru.invbeans.model.dto.AttemptDto;
import ru.invbeans.model.mapper.AttemptMapper;
import ru.invbeans.repository.AttemptRepository;
import ru.invbeans.service.AttemptService;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttemptServiceImpl implements AttemptService {
    private final AttemptRepository repository;
    private final AttemptMapper mapper;

    @Override
    public Long saveAttempt(AttemptDto dto){
        Attempt attempt = mapper.toModel(dto);
        repository.save(attempt);
        return attempt.getId();
    }

    @Override
    public List<Attempt> getAttempts(){
        List<Attempt> attemptList = (List<Attempt>) repository.findAll();
        return attemptList;
    }

    @Override
    public Optional<Attempt> getAttemptById(Long id){
        Optional<Attempt> attempt = repository.findById(id);
        return attempt;
    }

    @Override
    public List<Attempt> getByGameId(Long game){
        List<Attempt> attemptList = repository.findByGame(game);
        return attemptList;
    }
}
