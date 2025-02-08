package com.example.tictactoe.Service;

import com.example.tictactoe.Dto.GetBoardResponse;
import com.example.tictactoe.Dto.InitializeResponse;
import com.example.tictactoe.Dto.MoveResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class GameComponent {
    protected String[][] board;
    protected int size;
    protected String currentPlayer;
    protected Boolean isGameOver = false;
    protected Boolean isDraw = false;

    public abstract InitializeResponse init(int size);
    public abstract MoveResponse playerMove(int row, int col);
    public abstract Boolean canPlay();
    public abstract Boolean checkWinner(String winner);
    public abstract GetBoardResponse getBoard();
    public abstract Boolean checkRow(String player);
    public abstract Boolean checkCol(String player);
    public abstract Boolean checkDiagonal1(String player);
    public abstract Boolean checkDiagonal2(String player);


}
