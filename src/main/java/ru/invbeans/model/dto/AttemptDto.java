package ru.invbeans.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class AttemptDto {
    private Long id;
    private Long game;
    private Integer guess;
    private Long time;
}
