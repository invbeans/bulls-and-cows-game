package ru.invbeans.model.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "attempt_table")
public class Attempt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attempt_id", nullable = false)
    private Long id;

    @Column(name = "game_id")
    private Long game;

    @Column(name = "guess")
    private Integer guess;

    @Column(name = "time")
    private Long time;
}
