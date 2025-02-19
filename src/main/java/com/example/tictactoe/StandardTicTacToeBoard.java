package com.example.tictactoe;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * Standard implementation of the Tic Tac Toe board strategy.
 * Manages board state, move validation, and win detection.
 */
@Slf4j
public class StandardTicTacToeBoard implements BoardStrategy {
    @Getter
    private static final int BOARD_SIZE = 3;

    @Getter
    private char[][] board;

    /**
     * Initializes a new Tic Tac Toe board with empty cells.
     */
    public StandardTicTacToeBoard() {
        board = new char[BOARD_SIZE][BOARD_SIZE];
        initializeBoard();
    }

    /**
     * Initializes the board by setting all cells to blank spaces.
     */
    private void initializeBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            Arrays.fill(board[i], ' ');
        }
        log.debug("Board initialized");
    }

    @Override
    public boolean makeMove(int row, int col, char player) {
        validateMoveArguments(row, col);
        
        if (board[row][col] != ' ') {
            log.error("Cell already occupied: row={}, col={}", row, col);
            return false;
        }
        
        board[row][col] = player;
        log.debug("Move made: player={}, row={}, col={}", player, row, col);
        return true;
    }

    @Override
    public char getCell(int row, int col) {
        validateMoveArguments(row, col);
        return board[row][col];
    }

    @Override
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

    @Override
    public boolean checkWin(char player) {
        // Check rows
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
        }
        
        // Check columns
        for (int j = 0; j < BOARD_SIZE; j++) {
            if (board[0][j] == player && board[1][j] == player && board[2][j] == player) {
                return true;
            }
        }
        
        // Check diagonals
        return (board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
               (board[0][2] == player && board[1][1] == player && board[2][0] == player);
    }

    @Override
    public char[][] getBoardState() {
        // Return a deep copy to prevent external modification
        char[][] copy = new char[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            System.arraycopy(board[i], 0, copy[i], 0, BOARD_SIZE);
        }
        return copy;
    }

    /**
     * Validates the row and column arguments for board operations.
     * 
     * @param row The row to validate
     * @param col The column to validate
     * @throws IllegalArgumentException if row or column are outside the board
     */
    private void validateMoveArguments(int row, int col) {
        if (row < 0 || row >= BOARD_SIZE || col < 0 || col >= BOARD_SIZE) {
            throw new IllegalArgumentException(
                "Invalid board position: row=" + row + ", col=" + col
            );
        }
    }
}