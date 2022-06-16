package ru.invbeans.model.domain;

import lombok.*;
import javax.persistence.*;
@Data
@RequiredArgsConstructor
@Entity
@Table(name = "game_table")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id", nullable = false)
    private Long id;

    @Column(name = "player")
    private Long player;

    @Column(name = "attempts")
    private int attempts;

    @Column(name = "time")
    private Long time;
}
