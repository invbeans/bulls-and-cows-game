package ru.invbeans.model.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import ru.invbeans.model.domain.Attempt;
import ru.invbeans.model.dto.AttemptDto;
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AttemptMapper {
    AttemptDto toDTO(Attempt model);

    Attempt toModel(AttemptDto dto);
}