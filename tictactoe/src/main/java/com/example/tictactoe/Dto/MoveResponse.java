package com.example.tictactoe.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MoveResponse {
    private String status;
    private String message;
    private String currentPlayer;
    private Boolean isGameOver;
    private Boolean isDraw;
}


