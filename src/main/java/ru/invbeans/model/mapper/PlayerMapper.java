package ru.invbeans.model.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import ru.invbeans.model.domain.Player;
import ru.invbeans.model.dto.PlayerDto;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PlayerMapper {
    PlayerDto toDTO(Player model);
    Player toModel(PlayerDto dto);
}
