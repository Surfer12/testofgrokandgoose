package com.example.tictactoe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Manages the game logic for a Tic Tac Toe game.
 * Uses dependency injection for the board strategy.
 */
public class TicTacToe {
    private static final Logger log = LoggerFactory.getLogger(TicTacToe.class);
    
    private final BoardStrategy board;
    private char currentPlayer;
    private boolean gameOver;

    /**
     * Constructs a new Tic Tac Toe game with a specific board strategy.
     * 
     * @param boardStrategy The board implementation to use for the game
     */
    public TicTacToe(BoardStrategy boardStrategy) {
        this.board = boardStrategy;
        this.currentPlayer = 'X';
        this.gameOver = false;
        log.info("New game started");
    }

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

    /**
     * Returns the current player ('X' or 'O').
     * 
     * @return The current player symbol
     */
    public char getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Checks if the game has ended (either by a win or a draw).
     * 
     * @return true if the game is over, false otherwise
     */
    public boolean isGameOver() {
        return gameOver;
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