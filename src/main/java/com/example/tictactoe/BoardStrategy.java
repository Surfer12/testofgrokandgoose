package com.example.tictactoe;

/**
 * Defines the contract for a Tic Tac Toe board implementation.
 * Allows for different board strategies and easier testing.
 */
public interface BoardStrategy {
    /**
     * Attempts to place a player's symbol on the board.
     *
     * @param row The row of the move (0-2)
     * @param col The column of the move (0-2)
     * @param player The player's symbol ('X' or 'O')
     * @return true if the move is valid and successful, false otherwise
     */
    boolean makeMove(int row, int col, char player);

    /**
     * Retrieves the symbol at a specific board position.
     *
     * @param row The row of the cell (0-2)
     * @param col The column of the cell (0-2)
     * @return The symbol at the specified cell ('X', 'O', or blank space)
     */
    char getCell(int row, int col);

    /**
     * Checks if the board is completely filled with no empty spaces.
     *
     * @return true if no empty cells remain, false otherwise
     */
    boolean isFull();

    /**
     * Checks if the specified player has won the game.
     *
     * @param player The player's symbol to check for winning configuration
     * @return true if the player has won, false otherwise
     */
    boolean checkWin(char player);

    /**
     * Retrieves a copy of the current board state.
     *
     * @return A 2D char array representing the current board
     */
    char[][] getBoardState();
}