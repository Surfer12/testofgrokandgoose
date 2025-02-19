package com.example.tictactoe;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TicTacToeBoard {
    private char[][] board;
    private static final int BOARD_SIZE = 3;

    public TicTacToeBoard() {
        board = new char[BOARD_SIZE][BOARD_SIZE];
        initializeBoard();
    }

    protected void initializeBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = ' ';
            }
        }
        log.debug("Board initialized");
    }

    public boolean makeMove(int row, int col, char player) {
        if (row < 0 || row >= BOARD_SIZE || col < 0 || col >= BOARD_SIZE) {
            log.error("Invalid move attempt: row={}, col={}", row, col);
            return false;
        }
        if (board[row][col] != ' ') {
            log.error("Cell already occupied: row={}, col={}", row, col);
            return false;
        }
        board[row][col] = player;
        log.debug("Move made: player={}, row={}, col={}", player, row, col);
        return true;
    }

    public char getCell(int row, int col) {
        return board[row][col];
    }

    public boolean isFull() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkWin(char player) {
        // Check rows
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                log.info("Player {} wins by row {}", player, i);
                return true;
            }
        }
        // Check columns
        for (int j = 0; j < BOARD_SIZE; j++) {
            if (board[0][j] == player && board[1][j] == player && board[2][j] == player) {
                log.info("Player {} wins by column {}", player, j);
                return true;
            }
        }
        // Check diagonals
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            log.info("Player {} wins by main diagonal", player);
            return true;
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            log.info("Player {} wins by secondary diagonal", player);
            return true;
        }
        return false;
    }

    public void printBoard() {
        StringBuilder boardState = new StringBuilder("\nCurrent Board State:\n");
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                boardState.append(board[i][j]).append(" ");
            }
            boardState.append("\n");
        }
        log.debug(boardState.toString());
    }
} 