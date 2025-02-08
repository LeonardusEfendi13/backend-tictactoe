package com.example.tictactoe.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MoveRequest {
    private Integer row;
    private Integer col;
}


