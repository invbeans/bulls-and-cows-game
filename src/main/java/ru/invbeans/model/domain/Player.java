package ru.invbeans.model.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "player_table")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id", nullable = false)
    private Long id;

    @Column(name = "nickname", length = 40, unique = true)
    private String nickname;

    @Column(name = "password", length = 40)
    private String password;
}
