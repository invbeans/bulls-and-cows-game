package ru.invbeans.service;

import org.springframework.stereotype.Service;
import ru.invbeans.model.domain.Check;

public interface GameLogicService {
    int generateNumber();

    Check checkAnswer(int answer, int guess);
}
