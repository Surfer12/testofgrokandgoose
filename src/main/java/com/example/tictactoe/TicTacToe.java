package com.example.tictactoe;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Manages the game logic for a Tic Tac Toe game.
 * Uses dependency injection for the board strategy.
 */
@Slf4j
@RequiredArgsConstructor
public class TicTacToe {
    // Final field for board strategy, enforcing immutability
    @NonNull
    @Getter
    private final BoardStrategy board;

    // Additional game state fields
    @Getter
    private char currentPlayer = 'X';

    @Getter
    private boolean gameOver = false;

    /**
     * Constructs a new Tic Tac Toe game with a standard board.
     */
    public TicTacToe() {
        this(new StandardTicTacToeBoard());
    }

    /**
     * Attempts to make a move for the current player.
     * 
     * @param row The row of the move (0-2)
     * @param col The column of the move (0-2)
     * @return true if the move results in a win, false otherwise
     * @throws TicTacToeException if the game is already over or the move is invalid
     */
    public boolean makeMove(int row, int col) {
        validateGameState();

        boolean moveSuccessful = board.makeMove(row, col, currentPlayer);
        
        if (!moveSuccessful) {
            log.error("Invalid move: row={}, col={}", row, col);
            throw new TicTacToeException("Invalid move");
        }

        return processMove(row, col);
    }

    /**
     * Validates the current game state before making a move.
     * 
     * @throws TicTacToeException if the game is already over
     */
    private void validateGameState() {
        if (gameOver) {
            log.warn("Game is already over");
            throw new TicTacToeException("Game is already over");
        }
    }

    /**
     * Processes the move after it has been validated.
     * 
     * @param row The row of the move
     * @param col The column of the move
     * @return true if the move results in a win, false otherwise
     */
    private boolean processMove(int row, int col) {
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

        switchCurrentPlayer();
        return false;
    }

    /**
     * Switches the current player after a valid move.
     */
    private void switchCurrentPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        log.debug("Next turn: Player {}", currentPlayer);
    }

    /**
     * Retrieves the symbol at a specific board position.
     * 
     * @param row The row of the cell (0-2)
     * @param col The column of the cell (0-2)
     * @return The symbol at the specified cell ('X', 'O', or empty)
     */
    public char getCell(int row, int col) {
        return board.getCell(row, col);
    }

    /**
     * Retrieves the current board state.
     * 
     * @return A copy of the current board state
     */
    public char[][] getBoard() {
        return board.getBoardState();
    }
}