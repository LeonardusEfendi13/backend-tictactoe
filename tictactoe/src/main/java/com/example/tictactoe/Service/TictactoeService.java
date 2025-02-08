package com.example.tictactoe.Service;


import com.example.tictactoe.Dto.GetBoardResponse;
import com.example.tictactoe.Dto.InitializeResponse;
import com.example.tictactoe.Dto.MoveResponse;
import org.springframework.stereotype.Service;

@Service
public class TictactoeService extends GameComponent {

    @Override
    public InitializeResponse init(int size) {
        this.size = size; // set variable size for other function if they want to use this variable
        this.board = new String[size][size]; // set size of the board in 2 dimensions array from user input
        this.currentPlayer = "X"; //Start from player 'X'
        this.isGameOver = false;

        //Fill all the board fields with an empty string
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = " ";
            }
        }
        return new InitializeResponse("success", "Initialized board with size " + size, currentPlayer);
    }

    @Override
    public MoveResponse playerMove(int row, int col) {

        //Check is game still running
        if(isGameOver){
            return new MoveResponse("error", "Game Over", currentPlayer, isGameOver, false);
        }

        //Check if board initalized or not
        if (board == null) {
            return new MoveResponse("error", "Board is not initalized yet", currentPlayer, isGameOver, false);
        }

        //Check is row or col input is more than size
        if(row > size-1 || col > size-1){
            System.out.println("input not valid");
            return new MoveResponse("error", "Input not valid", currentPlayer, isGameOver, false);
        }


        //Check if position is occupied
        if (!board[row][col].equalsIgnoreCase(" ")) {
            return new MoveResponse("error","Position is occupied", currentPlayer, isGameOver, false);
        }

        //Set value to board
        board[row][col] = currentPlayer;

        //Check is all position is occupied
        if(!canPlay()){
            isGameOver = true;
            return new MoveResponse("success", "Game is draw", currentPlayer, isGameOver, true);
        }

        if (checkWinner(currentPlayer)) {
            System.out.println("Player : " + currentPlayer + " is the winner");
            isGameOver = true;
            return new MoveResponse("success", "winner is " + currentPlayer, currentPlayer, isGameOver, false);
        }



        //Switch player
        if (currentPlayer.equalsIgnoreCase("X")) {
            currentPlayer = "O";
        } else {
            currentPlayer = "X";
        }

        return new MoveResponse("success", "Switched to " + currentPlayer, currentPlayer, isGameOver, false);
    }

    public GetBoardResponse getBoard() {
        if(board == null){
            return new GetBoardResponse("error", null, null);
        }
        return new GetBoardResponse("success", board, size);
    }

    public Boolean canPlay(){
        int counter = 0;
        for (int col = 0; col < size; col++) {
            for (int row = 0; row < size; row++) {
                if (board[row][col].equals(" ")) {
                    counter++;
                }
            }
        }
        return counter != 0;
    }

    public Boolean checkWinner(String player) {
        //Check for row
        return checkRow(player) || checkCol(player) || checkDiagonal1(player) || checkDiagonal2(player);
    }

    public Boolean checkRow(String player) {
        for (int row = 0; row < size; row++) {
            int sameCharacterCounter = 0;
            for (int col = 0; col < size; col++) {
                if (board[row][col].equals(player)) {
                    sameCharacterCounter++;
                }
            }
            if (sameCharacterCounter == size) {
                return true;
            }
        }

        return false;
    }

    public Boolean checkCol(String player) {
        for (int col = 0; col < size; col++) {
            int sameCharacterCounter = 0;
            for (int row = 0; row < size; row++) {
                if (board[row][col].equals(player)) {
                    sameCharacterCounter++;
                }
            }
            if (sameCharacterCounter == size) {
                return true;
            }
        }

        return false;
    }

    public Boolean checkDiagonal1(String player) {
        int sameCharacterCounter = 0;
        for (int i = 0; i < size; i++) {
            if (board[i][i].equals(player)) {
                sameCharacterCounter++;
            }
        }
        return sameCharacterCounter == size;
    }

    public Boolean checkDiagonal2(String player) {
        int sameCharacterCounter = 0;
        for (int i = 0; i < size; i++) {
            if (board[i][size - 1 - i].equals(player)) {
                sameCharacterCounter++;
            }
        }
        return sameCharacterCounter == size;
    }
}
