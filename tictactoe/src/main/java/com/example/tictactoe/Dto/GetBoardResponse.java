package com.example.tictactoe.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetBoardResponse {
    private String status;
    private String[][] data;
    private Integer size;
}

