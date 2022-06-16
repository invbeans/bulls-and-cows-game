package ru.invbeans.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class GameDto {
    private Long id;
    private Long player;
    private int attempts;
    private Long time;
}
