package com.example.tictactoe;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StrictBoardStrategy extends TicTacToeBoard {
    private char currentPlayer = 'X';  // X starts by default
    private boolean gameOver = false;

    @Override
    public boolean makeMove(int row, int col, char player) {
        // Validate player turn
        if (player != currentPlayer) {
            log.error("Invalid turn: Expected player {}, got {}", currentPlayer, player);
            throw new IllegalStateException("Out of turn move");
        }

        // Validate game is not over
        if (gameOver) {
            log.error("Attempted move after game is over");
            throw new IllegalStateException("Game is already over");
        }

        // Perform standard move validation
        boolean moveResult = super.makeMove(row, col, player);
        
        if (moveResult) {
            // Check for win or draw after successful move
            if (checkWin(player)) {
                log.info("Game over: Player {} wins!", player);
                gameOver = true;
            } else if (isFull()) {
                log.info("Game over: Draw!");
                gameOver = true;
            }

            // Switch player
            currentPlayer = (player == 'X') ? 'O' : 'X';
        }

        return moveResult;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }

    // Optional: Add method to reset game state
    public void resetGame() {
        super.initializeBoard();
        currentPlayer = 'X';
        gameOver = false;
        log.debug("Game reset to initial state");
    }
} 