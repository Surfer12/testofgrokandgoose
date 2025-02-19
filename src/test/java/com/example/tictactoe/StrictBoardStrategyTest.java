package com.example.tictactoe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StrictBoardStrategyTest {

    private StrictBoardStrategy board;

    @BeforeEach
    void setUp() {
        board = new StrictBoardStrategy();
    }

    @Test
    void testAlternatingTurns() {
        // First move by X
        assertTrue(board.makeMove(0, 0, 'X'));
        assertEquals('O', board.getCurrentPlayer());

        // Next move must be by O
        assertTrue(board.makeMove(1, 1, 'O'));
        assertEquals('X', board.getCurrentPlayer());
    }

    @Test
    void testOutOfTurnMove() {
        // First move by X
        board.makeMove(0, 0, 'X');

        // Attempt to move by X again (out of turn)
        assertThrows(IllegalStateException.class, () -> {
            board.makeMove(1, 1, 'X');
        });
    }

    @Test
    void testGameOverMoves() {
        // Simulate a win scenario
        board.makeMove(0, 0, 'X');  // X's turn
        board.makeMove(1, 1, 'O');  // O's turn
        board.makeMove(0, 1, 'X');  // X's turn
        board.makeMove(1, 0, 'O');  // O's turn
        board.makeMove(0, 2, 'X');  // X's turn (wins)

        assertTrue(board.isGameOver());

        // Attempt to make a move after game is over
        assertThrows(IllegalStateException.class, () -> {
            board.makeMove(2, 2, 'O');
        });
    }

    @Test
    void testGameReset() {
        // Play a game
        board.makeMove(0, 0, 'X');
        board.makeMove(1, 1, 'O');
        board.makeMove(0, 1, 'X');
        board.makeMove(1, 0, 'O');
        board.makeMove(0, 2, 'X');

        assertTrue(board.isGameOver());

        // Reset the game
        board.resetGame();

        // Verify game state is reset
        assertFalse(board.isGameOver());
        assertEquals('X', board.getCurrentPlayer());
        assertEquals(' ', board.getCell(0, 0));
    }
} 