package com.example.tictactoe;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TicTacToe {
    private TicTacToeBoard board;
    private char currentPlayer;
    private boolean gameOver;

    public TicTacToe() {
        board = new TicTacToeBoard();
        currentPlayer = 'X';
        gameOver = false;
        log.info("New game started");
    }

    public boolean makeMove(int row, int col) {
        if (gameOver) {
            log.warn("Game is already over");
            throw new TicTacToeException("Game is already over");
        }

        if (!board.makeMove(row, col, currentPlayer)) {
            log.error("Invalid move: row={}, col={}", row, col);
            throw new TicTacToeException("Invalid move");
        }

        if (board.checkWin(currentPlayer)) {
            gameOver = true;
            log.info("Player {} wins!", currentPlayer);
            return true;
        }

        if (board.isFull()) {
            gameOver = true;
            log.info("Game ends in a draw");
            return false;
        }

        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        log.debug("Next turn: Player {}", currentPlayer);
        return false;
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public char getCell(int row, int col) {
        return board.getCell(row, col);
    }
}