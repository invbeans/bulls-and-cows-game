package ru.invbeans.model.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import ru.invbeans.model.domain.Game;
import ru.invbeans.model.dto.GameDto;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface GameMapper {
    GameDto toDTO(Game model);
    Game toModel(GameDto dto);
}
